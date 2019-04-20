//
// Created by julien on 20/04/2019.
//
#pragma once

#include "coords.h"

namespace math {
    // Template
    template<class I, size_t DEG> struct Coords<I,DEG,true>: _coords<I,DEG> {
        // Constructeurs
        Coords() = default;
        Coords(Coords const& c): _coords<I,DEG>(c) {};
        template<class... Args> explicit Coords(Args const&... v): _coords<I,DEG>(v...) {};

        // Opérateurs
        Coords& operator += (Coords const& c) {
            for (size_t i = 0; i < DEG; ++i) {
                _coords<I,DEG>::operator[](i) += c[i];
            }

            return *this;
        }
        Coords& operator -= (Coords const& c) {
            for (size_t i = 0; i < DEG; ++i) {
                _coords<I,DEG>::operator[](i) -= c[i];
            }

            return *this;
        }

        Coords operator + (Coords const& pt) const {
            Coords r(*this); r += pt; return r;
        }
        Coords operator - (Coords const& pt) const {
            Coords r(*this); r -= pt; return r;
        }
    };

    // Alias
    template<class I, size_t DEG> using Point = Coords<I,DEG,true>;

    using Point2i = Point<int,2>;
    using Point3i = Point<int,3>;
    using Point4i = Point<int,4>;

    using Point2f = Point<float,2>;
    using Point3f = Point<float,3>;
    using Point4f = Point<float,4>;
}