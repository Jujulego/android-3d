//
// Created by julien on 20/04/2019.
//
#pragma once

#include <jni.h>

#include "envref.h"
#include "meta.h"

namespace jni {
    // Templates
    template<class T> struct localref {
        // Attributs
        envref m_env;
        typename std::enable_if<is_jobject<T>::value,T>::type m_ref;

        // Constructeur
        localref(JNIEnv* env, T ref): m_env(env), m_ref(ref) {}

        // Destructeur
        ~localref() {
            m_env->DeleteLocalRef(m_ref);
        }

        // Opérateurs
        operator T () const { return m_ref; }
    };
}