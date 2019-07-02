//
// Created by julien on 01/07/2019.
//
#include <mutex>

#include <GLES3/gl32.h>

#include "buffer.h"

// Constructor
Buffer::Buffer() {
    generate();
}

// Destructor
Buffer::~Buffer() {
    unbound();
    destroy();
}

// Methods
void Buffer::generate() noexcept {
    std::lock_guard<std::recursive_mutex> lck(m_mtx);

    if (!isGenerated()) {
        glGenBuffers(1, &m_id);
    }
}

void Buffer::destroy() noexcept {
    std::lock_guard<std::recursive_mutex> lck(m_mtx);

    if (isGenerated()) {
        glDeleteBuffers(1, &m_id);
        m_id = GL_INVALID_INDEX;
    }
}

void Buffer::bound(GLenum target) {
    std::lock_guard<std::recursive_mutex> lck(m_mtx);

    if (isGenerated()) {
        if (m_bounded == 0) {
            glBindBuffer(target, m_id);
            m_target = target;
        }

        ++m_bounded;
    } else {
        throw std::runtime_error("buffer needs to be generated before being bounded");
    }
}

void Buffer::set(GLsizeiptr size, GLvoid const *data, GLenum usage) {
    std::lock_guard<std::recursive_mutex> lck(m_mtx);

    if (isBounded()) {
        glBufferData(m_target, size, data, usage);
        m_size = size;
    } else {
        throw std::runtime_error("buffer needs to be bound before being allocated");
    }
}

void Buffer::allocate(GLsizeiptr size, GLenum usage) {
    set(size, nullptr, usage);
}

void Buffer::update(GLintptr offset, GLsizeiptr size, GLvoid const* data) const {
    std::lock_guard<std::recursive_mutex> lck(m_mtx);

    // Checks
    if (offset >= m_size) {
        throw std::overflow_error("offset is greater than size");
    }

    if (offset + size >= m_size) {
        throw std::overflow_error("offset + size is greater than size");
    }

    if (isBounded()) {
        glBufferSubData(m_target, offset, size, data);
    } else {
        throw std::runtime_error("buffer needs to be bound before being updated");
    }
}

void Buffer::unbound() noexcept {
    std::lock_guard<std::recursive_mutex> lck(m_mtx);

    if (isGenerated() && isBounded()) {
        --m_bounded;

        if (m_bounded == 0) {
            glBindBuffer(m_target, 0);
        }
    }
}

// Accessors
bool Buffer::isGenerated() const noexcept {
    return m_id == GL_INVALID_INDEX;
}

bool Buffer::isBounded() const noexcept {
    return m_bounded > 0;
}

GLuint const& Buffer::target() const noexcept {
    return m_target;
}

GLsizeiptr const& Buffer::size() const noexcept {
    return m_size;
}

GLenum Buffer::usage() const {
    std::lock_guard<std::recursive_mutex> lck(m_mtx);

    GLint usage;
    glGetBufferParameteriv(m_target, GL_BUFFER_USAGE, &usage);

    return (GLenum) usage;
}