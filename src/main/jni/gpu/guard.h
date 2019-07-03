//
// Created by julien on 02/07/19.
//
#pragma once

#include <GLES3/gl32.h>

#include "buffer.h"

namespace gpu {
    // Class
    class bound_guard {
    private:
        // Attributes
        Buffer& m_buffer;
        bool m_bounded = false;

    public:
        // Constructors
        bound_guard(Buffer& buffer, GLenum target);

        // Destructor
        ~bound_guard();

        // Methods
        void unbound() noexcept;
    };
}