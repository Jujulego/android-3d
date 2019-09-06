//
// Created by julien on 06/09/2019.
//
#pragma once

#include <android/log.h>
#include <jni.h>

#include "envref.h"
#include "meta.h"

#define LOG_DEBUG(...) __android_log_print(ANDROID_LOG_DEBUG, "jni::globalref", __VA_ARGS__)

namespace jni {
    // Templates
    template<class T> struct globalref {
        // Attributes
        envref m_env;
        typename std::enable_if<is_jobject<T>::value,T>::type m_ref;

        // Constructors
        globalref(JNIEnv* env, T ref): m_env(env), m_ref((T) env->NewGlobalRef(ref)) {}
        globalref(globalref const& obj): m_env(obj.m_env), m_ref((T) obj.m_env->NewGlobalRef(obj.m_ref)) {}

        // Destructors
        ~globalref() {
            m_env->DeleteGlobalRef(m_ref);
        }

        // Operators
        operator T () const { return m_ref; }

        globalref& operator = (globalref const& obj) {
            m_env->DeleteGlobalRef(m_ref);

            m_env = obj.m_env;
            m_ref = (T) m_env->NewGlobalRef(obj.m_ref);

            return *this;
        }
    };
}

#undef LOG_DEBUG