//
// Created by julien on 20/04/2019.
//
#pragma once

#include <jni.h>
#include <memory>

namespace jni {
    // Classes
    class JNIClass: public std::enable_shared_from_this<JNIClass> {
    private:
        // Attributs
        unsigned long handle = 0UL;

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
    std::shared_ptr<JNIClass> _fromHandle(jlong handle);
    template<class C> std::shared_ptr<C> fromHandle(jlong handle) {
        return std::dynamic_pointer_cast<C>(_fromHandle(handle));
    }
}

