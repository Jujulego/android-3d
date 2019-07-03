//
// Created by julien on 02/07/19.
//
#include "guard.h"

using namespace gpu;

// Constructor
bound_guard::bound_guard(Buffer &buffer, GLenum target): m_buffer(buffer) {
    m_buffer.bound(target);
    m_bounded = true;
}

// Destructor
bound_guard::~bound_guard() {
    unbound();
}

// Methods
void bound_guard::unbound() noexcept {
    if (m_bounded) m_buffer.unbound();
}