//
// Created by julien on 01/09/2019.
//
#pragma once

#include <string>

#include <GLES3/gl32.h>

#include "jnitools.h"

#include "buffer.h"

namespace gpu {
    // Class
    class Attribute: public jni::JNIClass {
    private:
        // Attributes
        std::string m_name;
        GLint m_known_index = GL_INVALID_INDEX;

        GLint m_index = GL_INVALID_INDEX;

        // Methods
        GLint getIndex(GLuint const& program) const;

    public:
        // Constructor
        Attribute(GLint const& index);
        Attribute(std::string const& name);

        // Methods
        void prepare(GLuint const& program);
    };
}