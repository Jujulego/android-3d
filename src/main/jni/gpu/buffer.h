//
// Created by julien on 29/08/2019.
//
#pragma once

#include <GLES3/gl32.h>

#include "jnitools.h"

namespace gpu {
    // Interfaces
    struct Bufferable {
        // Destructor
        virtual ~Bufferable() = default;

        // Abstract methods
        virtual GLsizeiptr getBufferSize() const = 0;
        virtual void const* getData() const = 0;
    };

    struct BufferableArray {
        // Destructor
        virtual ~BufferableArray() = default;

        // Abstract methods
        virtual GLsizeiptr getBufferSize() const = 0;

        virtual size_t getBufferElementCount() const = 0;
        virtual Bufferable const& getBufferElement(size_t const& i) const = 0;
    };

    // Class
    class Buffer: public jni::JNIClass {
    private:
        // Attributes
        GLuint m_buffer = GL_INVALID_INDEX;
        GLenum m_target = GL_INVALID_ENUM;

        // Methods
        void generate();
        void destroy();

    public:
        // Constructor
        Buffer();

        // Destructor
        ~Buffer();

        // Methods
        void regenerate();

        void bound(GLenum const& target);
        void setData(Bufferable const& data, GLenum const& usage);
        void setDataArray(BufferableArray const& data, GLenum const& usage);
        void unbound();
    };
}