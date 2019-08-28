//
// Created by julien on 28/08/2019.
//
#pragma once

#include <list>
#include <memory>

#include <GLES3/gl32.h>

#include "jnitools.h"
#include "shader.h"

namespace gpu {
    // Error
    class ProgramError: public std::exception {
    private:
        // Attributes
        std::string m_error;

    public:
        // Constructor
        ProgramError(std::string const& error);

        // Methods
        std::string const& error() const;
        const char* what() const noexcept override;
    };

    // Class
    class Program: public jni::JNIClass {
    private:
        // Attributes
        GLuint m_program = GL_INVALID_INDEX;
        std::list<std::shared_ptr<Shader>> m_shaders;

    public:
        // Methods
        void addShader(std::shared_ptr<Shader> const& shader);

        void compile();
        void use();
        void destroy();
    };
}