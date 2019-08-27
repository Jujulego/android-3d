//
// Created by julie on 28/08/2019.
//
#pragma once

#include <string>

#include <GLES3/gl32.h>

namespace gpu {
    // Class
    class Shader {
    private:
        // Attributes
        GLenum m_type;
        GLuint m_shader = GL_INVALID_INDEX;

        bool m_compiled = false;

    public:
        // Constructors
        Shader(GLenum const& type);

        // Methods
        void compile();

        std::string getSource() const;
        void setSource(std::string const& source);

        bool hasError() const;
        std::string getError() const;
    };
}