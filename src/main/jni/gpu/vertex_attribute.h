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
    class VertexAttribute: public jni::JNIClass {
    private:
        // Attributes
        std::string m_name;
        GLuint m_location = GL_INVALID_INDEX;
        GLenum m_type;
        GLint m_size;

        GLuint m_index = GL_INVALID_INDEX;

        // Methods
        GLuint getIndex(GLuint const& program) const;

    public:
        // Constructor
        VertexAttribute(GLuint const& location, GLenum const& type, GLint const& size);
        VertexAttribute(std::string const& name, GLenum const& type, GLint const& size);

        // Methods
        void prepare(GLuint const& program);
        void enable(GLsizei const& stride, GLsizeiptr const& offset = 0, bool normalized = false);
    };
}