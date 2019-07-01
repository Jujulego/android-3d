//
// Created by julien on 01/07/2019.
//
#pragma once

#include <vector>
#include <GLES3/gl32.h>

// Class
class Buffer {
private:
    // Attributes
    GLuint m_target;
    GLenum m_id = GL_INVALID_INDEX;
    GLsizeiptr m_size;

    unsigned m_bounded = 0;

protected:
    // Constructors
    Buffer();

    // Methods
    void generate() noexcept;
    void destroy() noexcept;

public:
    // Destructor
    virtual ~Buffer();

    // Methods
    void bound(GLenum target);
    void unbound() noexcept;

    void allocate(GLsizeiptr size, GLenum usage = GL_STATIC_DRAW);
    void set(GLsizeiptr size, GLvoid const *data, GLenum usage = GL_STATIC_DRAW);
    void update(GLintptr offset, GLsizeiptr size, GLvoid const* data) const;

    // Accessors
    bool isGenerated() const;
    bool isBounded() const;

    GLuint const& target() const;
    GLsizeiptr const& size() const;
    GLenum usage() const;
};