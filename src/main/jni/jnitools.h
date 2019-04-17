//
// Created by julie on 17/04/2019.
//
#pragma once

#include <jni.h>
#include <memory>
#include <string>

#include "utils.h"

namespace jni {
    // Macros
    #define JNIFIELD(type, jname)                                                               \
        template<> struct field<type>: _field<type> {                                           \
            field(JNIEnv* env, jfieldID jfld, jobject jthis) : _field(env, jfld, jthis) {}      \
                                                                                                \
            type get() const override {                                                         \
                return m_env->Get##jname##Field(m_jthis, m_jfld);                               \
            }                                                                                   \
                                                                                                \
            void set(type const& val) override {                                                \
                m_env->Set##jname##Field(m_jthis, m_jfld, val);                                 \
            }                                                                                   \
        }

    #define JNIMETHOD(type, jname)                                                              \
        template<> struct method<type>: _method<type> {                                         \
            method(JNIEnv *env, jmethodID jmth, jobject jthis) : _method(env, jmth, jthis) {}   \
                                                                                                \
            template<class... Args> type operator () (Args const&... args) const override {     \
                return m_env->Call##jname##Method(m_jthis, m_jmth, args...);                    \
            }                                                                                   \
        }

    // Métafonctions
    template<class O> struct is_jobject : std::is_base_of<_jobject,typename std::remove_pointer<O>::type> {};

    // Classe
    class JNIClass: std::enable_shared_from_this<JNIClass> {
    private:
        // Attributs
        unsigned long handle;

    public:
        // Constructeur
        JNIClass();

        // Destructeur
        ~JNIClass();

        // Méthodes
        unsigned long get_handle();
    };

    // Templates
    // - bases
    template<class R> struct _field {
        // Attributs
        JNIEnv* m_env;
        jobject m_jthis;
        jfieldID m_jfld;

        // Constructeur
        _field(JNIEnv* env, jfieldID jfld, jobject jobj) : m_env(env), m_jthis(jobj), m_jfld(jfld) {}

        // Destructeur
        virtual ~_field() = default;

        // Méthodes
        virtual R get() const {
            return fromJava<R>(m_env, m_env->GetObjectField(m_jthis, m_jfld));
        }

        virtual void set(R const& val) {
            localref<jobject> jobj = toJava(m_env, val);
            m_env->SetObjectField(m_jthis, m_jfld, *jobj);
        }
    };
    template<class R> struct _method {
        // Attributs
        JNIEnv* m_env;
        jobject m_jthis;
        jmethodID m_jmth;

        // Constructeur
        _method(JNIEnv* env, jmethodID jmth, jobject jobj) : m_env(env), m_jthis(jobj), m_jmth(jmth) {}

        // Destructeur
        virtual ~_method() = default;

        // Opérateurs
        template<class... Args> virtual R operator () (Args const&... args) const {
            return fromJava(m_env, m_env->CallObjectMethod(m_jthis, m_jmth, args...));
        }
    };

    // - implémentations
    template<class T> struct localref {
        // Attributs
        JNIEnv* m_env;
        T m_ref;

        // Constructeur
        localref(JNIEnv* env, std::enable_if<is_jobject<T>::value>::type ref): m_env(env), m_ref(ref) {}

        // Destructeur
        ~localref() {
            m_env->DeleteLocalRef(m_ref);
        }

        // Opérateurs
        T operator * () { return m_ref; }
    };

    template<class R=jobject,class=void> struct field: _field<R> {
        // Constructeur
        field(JNIEnv* env, jfieldID jfld, jobject jthis): _field(env, jfld, jthis) {}
    };
    template<class R> struct field<R,typename std::enable_if<is_jobject<R>::value>::type>: _field<R> {
        // Constructeur
        field(JNIEnv* env, jfieldID jfld, jobject jthis): _field(env, jfld, jthis) {}

        // Méthodes
        localref<R> get() const override {
            return localref(m_env, (R) m_env->GetObjectField(m_jthis, m_jfld));
        }

        void set(R const& val) override {
            m_env->SetObjectField(m_jthis, m_jfld, (jobject) val);
        }
    };

    JNIFIELD(jboolean, Boolean);
    JNIFIELD(jchar,    Char);
    JNIFIELD(jbyte,    Byte);
    JNIFIELD(jshort,   Short);
    JNIFIELD(jint,     Int);
    JNIFIELD(jlong,    Long);
    JNIFIELD(jfloat,   Float);
    JNIFIELD(jdouble,  Double);

    template<class R=jobject,class=void> struct method: _method<R> {
        // Constructeur
        method(JNIEnv *env, jmethodID jmth, jobject jobj) : _method(env, jmth, jobj) {}
    };
    template<class R> struct method: _method<R> {
        // Constructeur
        method(JNIEnv *env, jmethodID jmth, jobject jobj) : _method(env, jmth, jobj) {}

        // Opérateur
        template<class... Args> localref<R> operator () (Args const&... args) const override {
            return localref<R>(m_env, (R) m_env->CallObjectMethod(m_jthis, m_jmth, args...));
        }
    };

    JNIMETHOD(void,     Void);
    JNIMETHOD(jboolean, Boolean);
    JNIMETHOD(jchar,    Char);
    JNIMETHOD(jbyte,    Byte);
    JNIMETHOD(jshort,   Short);
    JNIMETHOD(jint,     Int);
    JNIMETHOD(jlong,    Long);
    JNIMETHOD(jfloat,   Float);
    JNIMETHOD(jdouble,  Double);

    // Outils
    // - convertions
    template<class R> R fromJava(JNIEnv* env, jobject jobj);
    template<class R> localref<jobject> toJava(JNIEnv* env, R const& obj);

    // - find objects
    localref<jclass> findClass(JNIEnv* env, jobject jobj);
    localref<jclass> findClass(JNIEnv* env, std::string const& nom);

    jfieldID findField(JNIEnv* env, jclass jcls, std::string const &nom, std::string const &type);
    template<class R> field<R> findField(JNIEnv* env, jobject jthis, std::string const& nom, std::string const& type) {
        localref<jclass> jcls = findClass(env, jthis);
        return field<R>(env, findField(env, *jcls, nom, type), jthis);
    }

    jmethodID findMethod(JNIEnv* env, jclass jcls, std::string const &nom, std::string const &sig);
    template<class R> method<R> findMethod(JNIEnv* env, jobject jthis, std::string const& nom, std::string const& sig) {
        localref<jclass> jcls = findClass(env, jthis);
        return method<R>(env, findMethod(env, *jcls, nom, sig), jthis);
    }

    // - construct
    template<class... Args> localref<jobject> construct(JNIEnv *env, jclass jcls, std::string const &sig, Args const &... args) {
        jmethodID constructor = findMethod(env, jcls, "<init>", sig);
        return localref<jobject>(env, env->NewObject(jcls, constructor, args...));
    }
    template<class... Args> localref<jobject> construct(JNIEnv *env, std::string const &cls, std::string const &sig, Args const &... args) {
        localref<jclass> jcls = findClass(env, cls);
        return localref<jobject>(env, construct(env, *jcls, sig, args...));
    }
}