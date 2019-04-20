//
// Created by julien on 20/04/2019.
//
#pragma once

#include <memory>

namespace jni {
    // Classes
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
}

