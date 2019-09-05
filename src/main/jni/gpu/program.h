//
// Created by julien on 28/08/2019.
//
#pragma once

#include <list>
#include <memory>

#include <GLES3/gl32.h>

#include "jnitools.h"

#include "error.h"
#include "shader.h"
#include "vertex_attribute.h"

namespace gpu {
    // Error
    class ProgramError: public GPUError {
    private:
        // Attributes
        std::string m_error;

    protected:
        // Methods
        std::string javaName() const override;

    public:
        // Constructor
        ProgramError(std::string const& error);
    };

    // Class
    class Program: public jni::JNIClass {
    private:
        // Attributes
        GLuint m_program = GL_INVALID_INDEX;

        std::list<std::shared_ptr<Shader>> m_shaders;
        std::list<std::shared_ptr<VertexAttribute>> m_attributes;

    public:
        // Methods
        void addShader(std::shared_ptr<Shader> const& shader);
        void addAttribute(std::shared_ptr<VertexAttribute> const& attribute);

        void compile();
        void use();
        void destroy();

        GLuint const& program() const;
    };
}