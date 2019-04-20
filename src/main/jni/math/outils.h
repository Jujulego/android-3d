//
// Created by julie on 20/04/2019.
//
#pragma once

#include "coords.h"

namespace math {
    // Templates
    template<class I, size_t DEG, bool PT> I&       x(Coords<I,DEG,PT>&       c) { return c[0]; }
    template<class I, size_t DEG, bool PT> I const& x(Coords<I,DEG,PT> const& c) { return c[0]; }

    template<class I, size_t DEG, bool PT> I&       y(Coords<I,DEG,PT>&       c) { return c[1]; }
    template<class I, size_t DEG, bool PT> I const& y(Coords<I,DEG,PT> const& c) { return c[1]; }

    template<class I, size_t DEG, bool PT> I&       z(Coords<I,DEG,PT>&       c) {
        static_assert(DEG >= 3, "DEG should be at least 3");
        return c[2];
    }
    template<class I, size_t DEG, bool PT> I const& z(Coords<I,DEG,PT> const& c) {
        static_assert(DEG >= 3, "DEG should be at least 3");
        return c[2];
    }

    template<class I, size_t DEG, bool PT> I&       a(Coords<I,DEG,PT>&       c) {
        static_assert(DEG >= 4, "DEG should be at least 4");
        return c[3];
    }
    template<class I, size_t DEG, bool PT> I const& a(Coords<I,DEG,PT> const& c) {
        static_assert(DEG >= 4, "DEG should be at least 4");
        return c[3];
    }
}