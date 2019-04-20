//
// Created by julien on 20/04/2019.
//
#pragma once

#include <jni.h>
#include <string>

#include "convert.h"
#include "meta.h"

// Macros
#define JNIMETHOD(type, jname)                                                              \
    template<> struct method<type>: _method<type> {                                         \
        method(JNIEnv *env, jmethodID jmth, jobject jthis) : _method(env, jmth, jthis) {}   \
                                                                                            \
        template<class... Args> type operator () (Args const&... args) const {              \
            return m_env->Call##jname##Method(m_jthis, m_jmth, args...);                    \
        }                                                                                   \
    }

namespace jni {
    // Base
    template<class R> struct _method {
        // Attributs
        JNIEnv* m_env;
        jobject m_jthis;
        jmethodID m_jmth;

    protected:
        // Constructeur
        _method(JNIEnv* env, jmethodID jmth, jobject jobj) : m_env(env), m_jthis(jobj), m_jmth(jmth) {}
    };

    // Templates
    template<class R=jobject,class=void> struct method: _method<R> {
        // Constructeur
        method(JNIEnv *env, jmethodID jmth, jobject jobj) : _method<R>(env, jmth, jobj) {}

        // Opérateurs
        template<class... Args> R operator () (Args const&... args) const {
            return fromJava(_method<R>::m_env, _method<R>::m_env->CallObjectMethod(_method<R>::m_jthis, _method<R>::m_jmth, args...));
        }
    };
    template<class R> struct method<R,typename std::enable_if<is_jobject<R>::value>::type>: _method<R> {
        // Constructeur
        method(JNIEnv *env, jmethodID jmth, jobject jobj) : _method<R>(env, jmth, jobj) {}

        // Opérateur
        template<class... Args> localref<R> operator () (Args const&... args) const {
            return localref<R>(_method<R>::m_env, (R) _method<R>::m_env->CallObjectMethod(_method<R>::m_jthis, _method<R>::m_jmth, args...));
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
}