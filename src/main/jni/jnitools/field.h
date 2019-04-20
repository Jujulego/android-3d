//
// Created by julien on 20/04/2019.
//
#pragma once

#include <jni.h>
#include <string>

#include "convert.h"
#include "localref.h"

// Macros
#define JNIFIELD(type, jname)                                                               \
    template<> struct field<type>: _field<type> {                                           \
        field(JNIEnv* env, jfieldID jfld, jobject jthis) : _field(env, jfld, jthis) {}      \
                                                                                            \
        type get() const {                                                                  \
            return m_env->Get##jname##Field(m_jthis, m_jfld);                               \
        }                                                                                   \
                                                                                            \
        void set(type const& val) {                                                         \
            m_env->Set##jname##Field(m_jthis, m_jfld, val);                                 \
        }                                                                                   \
    }

namespace jni {
    // Base
    template<class R> struct _field {
        // Attributs
        JNIEnv* m_env;
        jobject m_jthis;
        jfieldID m_jfld;

    protected:
        // Constructeur
        _field(JNIEnv* env, jfieldID jfld, jobject jobj) : m_env(env), m_jthis(jobj), m_jfld(jfld) {}
    };

    // Templates
    template<class R=jobject,class=void> struct field: _field<R> {
        // Constructeur
        field(JNIEnv* env, jfieldID jfld, jobject jthis): _field<R>(env, jfld, jthis) {}

        // Méthodes
        R get() const {
            return fromJava<R>(_field<R>::m_env, _field<R>::m_env->GetObjectField(_field<R>::m_jthis, _field<R>::m_jfld));
        }

        void set(R const& val) {
            localref<jobject> jobj = toJava(_field<R>::m_env, val);
            _field<R>::m_env->SetObjectField(_field<R>::m_jthis, _field<R>::m_jfld, jobj);
        }
    };
    template<class R> struct field<R,typename std::enable_if<is_jobject<R>::value>::type>: _field<R> {
        // Constructeur
        field(JNIEnv* env, jfieldID jfld, jobject jthis): _field<R>(env, jfld, jthis) {}

        // Méthodes
        localref<R> get() const {
            return localref(_field<R>::m_env, (R) _field<R>::m_env->GetObjectField(_field<R>::m_jthis, _field<R>::m_jfld));
        }

        void set(R const& val) {
            _field<R>::m_env->SetObjectField(_field<R>::m_jthis, _field<R>::m_jfld, (jobject) val);
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
}