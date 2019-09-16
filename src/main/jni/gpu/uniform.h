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

    // Interface
    struct Uniformable {
        // Destructor
        virtual ~Uniformable() = default;

        // Methods
        virtual void toUniform(GLint const& location) const = 0;
    };

    // Class
    class Uniform: public jni::JNIClass {
    private:
        // Attributes
        std::string m_name;
        Program const* m_program;

        GLint m_index = GL_INVALID_INDEX;

    public:
        // Constructor
        Uniform(std::string const& name, Program const* program);

        // Methods
        void prepare();
        void setValue(Uniformable const& value);
    };
}
