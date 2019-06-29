//
// Created by julie on 20/04/2019.
//
#pragma once

#include "vector.h"

namespace math {
    // Templates
    template<class I, size_t DEG> I&       x(Vector<I,DEG>&       v) { return v[0]; }
    template<class I, size_t DEG> I const& x(Vector<I,DEG> const& v) { return v[0]; }

    template<class I, size_t DEG> I&       y(Vector<I,DEG>&       v) { return v[1]; }
    template<class I, size_t DEG> I const& y(Vector<I,DEG> const& v) { return v[1]; }

    template<class I, size_t DEG> I&       z(Vector<I,DEG>&       v) {
        static_assert(DEG >= 3, "DEG should be at least 3");
        return v[2];
    }
    template<class I, size_t DEG> I const& z(Vector<I,DEG> const& v) {
        static_assert(DEG >= 3, "DEG should be at least 3");
        return v[2];
    }

    template<class I, size_t DEG> I&       a(Vector<I,DEG>&       v) {
        static_assert(DEG >= 4, "DEG should be at least 4");
        return v[3];
    }
    template<class I, size_t DEG> I const& a(Vector<I,DEG> const& v) {
        static_assert(DEG >= 4, "DEG should be at least 4");
        return v[3];
    }
}