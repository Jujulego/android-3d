//
// Created by julien on 22/04/2019.
//
#pragma once

#include <algorithm>
#include <array>
#include <type_traits>

#include "vector.h"

namespace math {
    // Alias
    using P = std::pair<size_t,size_t>;

    // Classe
    template<class I,size_t LIG,size_t COL> class Matrix {
        static_assert(std::is_arithmetic<I>::value, "I must be an arithmetic type");

    private:
        // Attributs
        std::array<I,LIG*COL> m_data;

        // Méthodes
        size_t to_index(size_t l, size_t c) const {
            return l + (c * LIG);
        }

    public:
        // Constructeurs
        Matrix() {
            for (size_t i = 0; i < LIG*COL; ++i) {
                m_data[i] = 0;
            }
        }
        Matrix(Matrix const& m): m_data(m.m_data) {}

        // Attributs
        I&       operator [] (P const& p)       { return m_data[to_index(p.first, p.second)]; }
        I const& operator [] (P const& p) const { return m_data[to_index(p.first, p.second)]; }

        bool operator == (Matrix const& m) const { return  std::equal(m_data.begin(), m_data.end(), m.m_data.begin()); }
        bool operator != (Matrix const& m) const { return !std::equal(m_data.begin(), m_data.end(), m.m_data.begin()); }

        Matrix operator + () const {
            Matrix r;

            for (size_t i = 0; i < LIG*COL; ++i) {
                r[i] = +m_data[i];
            }

            return r;
        }
        Matrix operator - () const {
            Matrix r;

            for (size_t i = 0; i < LIG*COL; ++i) {
                r[i] = -m_data[i];
            }

            return r;
        }

        Matrix& operator += (Matrix const& m) {
            for (size_t i = 0; i < LIG*COL; ++i) {
                m_data[i] += m.m_data[i];
            }

            return *this;
        }
        Matrix& operator -= (Matrix const& m) {
            for (size_t i = 0; i < LIG*COL; ++i) {
                m_data[i] -= m.m_data[i];
            }

            return *this;
        }
        Matrix& operator *= (I const& k) {
            for (size_t i = 0; i < LIG*COL; ++i) {
                m_data[i] *= k;
            }

            return *this;
        }
        Matrix& operator /= (I const& k) {
            for (size_t i = 0; i < LIG*COL; ++i) {
                m_data[i] /= k;
            }

            return *this;
        }

        Matrix operator + (Matrix const& m) const {
            Matrix r(*this); r += m; return r;
        }
        Matrix operator - (Matrix const& m) const {
            Matrix r(*this); r -= m; return r;
        }
        Matrix operator * (I const& k) const {
            Matrix r(*this); r *= k;
            return r;
        }
        Matrix operator / (I const& k) const {
            Matrix r(*this); r /= k;
            return r;
        }

        // Méthodes
        Vector<I,COL> lig(size_t l) const {
            Vector<I,COL> v;
            for (size_t c = 0; c < COL; ++c) {
                v[c] = m_data[to_index(l, c)];
            }

            return v;
        }
        Vector<I,LIG> col(size_t c) const {
            Vector<I,LIG> v;
            for (size_t l = 0; l < LIG; ++l) {
                v[l] = m_data[to_index(l, c)];
            }

            return v;
        }

        P size() const {
            return P(LIG,COL);
        }
    };
}

// Opérateurs externes
template<class I,size_t LIG,size_t COL>
math::Matrix<I,LIG,COL> operator * (I const& k, math::Matrix<I,LIG,COL> const& m) {
    return m * k;
}