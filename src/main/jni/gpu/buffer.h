//
// Created by julien on 01/07/2019.
//
#pragma once

#include <mutex>
#include <vector>

#include <GLES3/gl32.h>

namespace gpu {
    // Class
    class Buffer {
    private:
        // Attributes
        GLenum m_target;
        GLuint m_id = GL_INVALID_INDEX;
        GLsizeiptr m_size;

        unsigned m_bounded = 0;
        mutable std::recursive_mutex m_mtx;

        // Methods
        void generate() noexcept;

        void destroy() noexcept;

    public:
        // Constructors
        Buffer();

        Buffer(Buffer const&) = delete;

        Buffer(Buffer&&) = delete;

        // Destructor
        virtual ~Buffer();

        // Methods
        void bound(GLenum target);

        void unbound() noexcept;

        void allocate(GLsizeiptr size, GLenum usage = GL_STATIC_DRAW);

        void set(GLsizeiptr size, GLvoid const* data, GLenum usage = GL_STATIC_DRAW);

        void update(GLintptr offset, GLsizeiptr size, GLvoid const* data) const;

        void copy(Buffer const& buffer, GLintptr from, GLintptr to, GLsizeiptr size) const;

        // Accessors
        bool isGenerated() const noexcept;

        bool isBounded() const noexcept;

        GLuint const& id() const noexcept;

        GLenum const& target() const noexcept;

        GLsizeiptr const& size() const noexcept;

        GLenum usage() const;
    };
}