//
// Created by julien on 20/04/2019.
//
#pragma once

#include <algorithm>
#include <array>
#include <type_traits>

#include "jnitools.h"

namespace math {
    // Template
    template<class I, size_t DEG, bool PT> struct Coords: jni::JNIClass {
        static_assert(DEG >= 2, "DEG should be at least 2");
        static_assert(std::is_arithmetic<I>::value, "I must be an arithmetic type");
    };
};