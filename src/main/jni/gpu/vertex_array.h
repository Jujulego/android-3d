//
// Created by julien on 04/09/2019.
//
#pragma once

#include <GLES3/gl32.h>

#include "jnitools.h"

namespace gpu {
    // Class
    class VertexArray: public jni::JNIClass {
    private:
        // Attributes
        GLuint m_index = GL_INVALID_INDEX;

    public:
        // Constructor
        VertexArray();

        // Methods
        void bind();
    };
}