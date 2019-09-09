//
// Created by julien on 09/09/2019.
//
#pragma once

#include <memory>
#include <string>

#include <GLES3/gl3.h>

#include "jnitools.h"

namespace gpu {
    // Names
    class Program;

    // Class
    class Uniform: public jni::JNIClass {
    private:
        // Attributes
        std::string m_name;
        Program const* m_program;

        GLuint m_index = GL_INVALID_INDEX;

    public:
        // Constructor
        Uniform(std::string const& name, Program const* program);

        // Methods
        void prepare();
    };
}
