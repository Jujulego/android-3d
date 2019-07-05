//
// Created by julien on 20/04/2019.
//
#pragma once

#include <jni.h>
#include <memory>

#include "convert.h"

#define INVALID_HANDLE 0UL

namespace jni {
    // Classes
    class JNIClass: public std::enable_shared_from_this<JNIClass> {
    private:
        // Attributs
        unsigned long handle = INVALID_HANDLE;

    public:
        // Destructeur
        virtual ~JNIClass();

        // Méthodes
        void register_jni(bool acq = false);
        void acquire();
        void dispose();

        jlong get_jhandle();
    };

    // Outils
    unsigned long getHandle(JNIEnv* env, jobject jobj);

    std::shared_ptr<JNIClass> _fromHandle(jlong handle);
    template<class C> std::shared_ptr<C> fromHandle(jlong handle) {
        return std::dynamic_pointer_cast<C>(_fromHandle(handle));
    }

    template<class R> std::shared_ptr<R> fromJava(JNIEnv* env, jobject jobj) {
        return fromHandle<R>(getHandle(env, jobj));
    }
}

