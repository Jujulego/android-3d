//
// Created by julien on 20/04/2019.
//
#pragma once

#include <algorithm>
#include <array>

#include "coords.h"
#include "jnitools.h"

namespace math {
    // Template
    template<class I, size_t DEG> class Coords<I,DEG,false>: public jni::JNIClass {
    private:
        // Attributs
        std::array<I,DEG> m_data;

    public:
        // Alias
        using value_type      = I;
        using reference       = I&;
        using const_reference = I const&;
        using pointer         = I*;
        using const_pointer   = I const*;
        using iterator        = typename std::array<I,DEG>::iterator;
        using const_iterator  = typename std::array<I,DEG>::const_iterator;
        using size_type       = size_t;
        using difference_type = ptrdiff_t;

        // Constructeurs
        Coords() {
            for (size_t i = 0; i < DEG; ++i) {
                m_data[i] = 0;
            }
        };
        Coords(Coords const& c): m_data(c.m_data) {};
        template<class... Args> explicit Coords(Args const&... v): m_data({v...}) {
            static_assert(sizeof...(Args) == DEG, "Need exactly DEG args");
        };

        // Opérateurs
        I&       operator [] (size_t i)       { return m_data[i]; }
        I const& operator [] (size_t i) const { return m_data[i]; }

        bool operator == (Coords const& c) const { return  std::equal(begin(), end(), c.begin()); }
        bool operator != (Coords const& c) const { return !std::equal(begin(), end(), c.begin()); }

        Coords operator + () const {
            Coords r;

            for (size_t i = 0; i < DEG; ++i) {
                r[i] = +m_data[i];
            }

            return r;
        }
        Coords operator - () const {
            Coords r;

            for (size_t i = 0; i < DEG; ++i) {
                r[i] = -m_data[i];
            }

            return r;
        }

        Coords& operator += (Coords const& c) {
            for (size_t i = 0; i < DEG; ++i) {
                m_data[i] += c[i];
            }

            return *this;
        }
        Coords& operator -= (Coords const& c) {
            for (size_t i = 0; i < DEG; ++i) {
                m_data[i] -= c[i];
            }

            return *this;
        }
        Coords& operator *= (I const& k) {
            for (size_t i = 0; i < DEG; ++i) {
                m_data[i] *= k;
            }

            return *this;
        }
        Coords& operator /= (I const& k) {
            for (size_t i = 0; i < DEG; ++i) {
                m_data[i] /= k;
            }

            return *this;
        }

        Coords operator + (Coords const& pt) const {
            Coords r(*this); r += pt; return r;
        }
        Coords operator - (Coords const& pt) const {
            Coords r(*this); r -= pt; return r;
        }
        I operator * (Coords const& pt) const {
            I r = 0ULL;
            for (size_t i = 0; i < DEG; ++i) {
                r += m_data[i] * pt[i];
            }

            return r;
        }
        Coords operator * (I const& k) const {
            Coords r(*this); r *= k;
            return r;
        }
        Coords operator / (I const& k) const {
            Coords r(*this); r /= k;
            return r;
        }

        // Méthodes
        size_t size() const {
            return DEG;
        }

        iterator       begin()       { return m_data.begin(); }
        const_iterator begin() const { return m_data.begin(); }
        iterator       end()         { return m_data.end();   }
        const_iterator end()   const { return m_data.end();   }
    };

    // Alias
    template<class I, size_t DEG> using Vector = Coords<I,DEG,false>;

    using Vec2i = Vector<int,2>;
    using Vec3i = Vector<int,3>;
    using Vec4i = Vector<int,4>;

    using Vec2f = Vector<float,2>;
    using Vec3f = Vector<float,3>;
    using Vec4f = Vector<float,4>;
}

// Macros JNI
#define VECTOR_JNI(cls, type, ...)          \
    COORD_JNI(cls, type, __VA_ARGS__)