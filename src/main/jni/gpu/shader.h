//
// Created by julien on 28/08/2019.
//
#pragma once

#include <exception>
#include <string>

#include <GLES3/gl32.h>

#include "jnitools.h"

#include "error.h"

namespace gpu {
    // Error
    class ShaderError: public GPUError {
    private:
        // Attributes
        std::string m_error;

    protected:
        // Methods
        std::string javaName() const override;

    public:
        // Constructor
        ShaderError(std::string const& error);
    };

    // Class
    class Shader: public jni::JNIClass {
    private:
        // Attributes
        GLenum m_type;
        std::string m_source;

        GLuint m_shader = GL_INVALID_INDEX;

    public:
        // Constructors
        Shader(GLenum const& type);

        // Methods
        void compile();
        void destroy();

        GLuint const& shader() const;
        std::string& source();
        std::string const& source() const;
    };
}