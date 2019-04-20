//
// Created by julien on 20/04/2019.
//
#pragma once

#include <jni.h>

#include "meta.h"

namespace jni {
    // Templates
    template<class T> struct localref {
        // Attributs
        JNIEnv* m_env;
        typename std::enable_if<is_jobject<T>::value,T>::type m_ref;

        // Constructeur
        localref(JNIEnv* env, T ref): m_env(env), m_ref(ref) {}

        // Destructeur
        ~localref() {
            m_env->DeleteLocalRef(m_ref);
        }

        // Opérateurs
        operator T () { return m_ref; }
    };
}