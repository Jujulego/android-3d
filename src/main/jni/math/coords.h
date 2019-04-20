//
// Created by julien on 20/04/2019.
//
#pragma once

#include <algorithm>
#include <array>
#include <type_traits>

#include "jnitools.h"

namespace math {
    // Base
    template<class I, size_t DEG> class _coords: public jni::JNIClass {
        static_assert(DEG >= 2, "DEG should be at least 2");
        static_assert(std::is_arithmetic<I>::value, "I must be an arithmetic type");

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
        _coords() {
            for (size_t i = 0; i < DEG; ++i) {
                m_data[i] = 0;
            }
        }
        _coords(_coords const& c): m_data(c.m_data) {}
        template<class... Args> explicit _coords(Args const&... v): m_data({v...}) {
            static_assert(sizeof...(Args) == DEG, "Need exactly DEG args");
        }

        // Destructeurs
        virtual ~_coords() = default;

        // Opérateurs
        I&       operator [] (size_t i)       { return m_data[i]; }
        I const& operator [] (size_t i) const { return m_data[i]; }

        bool operator == (_coords const& c) const { return  std::equal(begin(), end(), c.begin()); }
        bool operator != (_coords const& c) const { return !std::equal(begin(), end(), c.begin()); }

        _coords operator + () const {
            _coords r;

            for (size_t i = 0; i < DEG; ++i) {
                r[i] = +m_data[i];
            }

            return r;
        }
        _coords operator - () const {
            _coords r;

            for (size_t i = 0; i < DEG; ++i) {
                r[i] = -m_data[i];
            }

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

    // Template
    template<class I, size_t DEG, bool PT> struct Coords: _coords<I,DEG> {};
};