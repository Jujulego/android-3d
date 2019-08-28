//
// Created by julie on 28/08/2019.
//
#pragma once

#include <string>

#include <GLES3/gl32.h>

#include "jnitools.h"

namespace gpu {
    // Class
    class Shader: public jni::JNIClass {
    private:
        // Attributes
        GLenum m_type;
        GLuint m_shader = GL_INVALID_INDEX;

    public:
        // Constructors
        Shader(GLenum const& type);

        // Methods
        void compile();
        GLuint const& shader() const;

        void setSource(std::string const& source);

        bool hasError() const;
        std::string getError() const;
    };
}