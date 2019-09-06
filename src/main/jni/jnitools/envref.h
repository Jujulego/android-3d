//
// Created by julien on 06/09/2019.
//
#pragma once

#include <jni.h>

namespace jni {
    // Class
    class envref {
    private:
        // Attributes
        JavaVM* m_vm = nullptr;
        jint m_version;

    public:
        // Constructor
        envref(JNIEnv* env);

        // Operators
        operator JNIEnv* () const;

        JNIEnv& operator * () const;
        JNIEnv* operator -> () const;

        // Methods
        JNIEnv* jenv() const;
    };
}