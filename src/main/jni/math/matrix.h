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
    template<class I,size_t LIG,size_t COL> class Matrix: public jni::JNIClass {
        static_assert(std::is_arithmetic<I>::value, "I must be an arithmetic type");

    private:
        // Attributs
        std::array<I,LIG*COL> m_data;

        // Méthodes
        size_t to_index(size_t c, size_t l) const {
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

        Matrix(I factors[]) {
            for (size_t i = 0; i < LIG*COL; ++i) {
                m_data[i] = factors[i];
            }
        }

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
            Matrix r(*this); r *= k; return r;
        }
        Matrix operator / (I const& k) const {
            Matrix r(*this); r /= k; return r;
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

        std::array<I,LIG*COL> const& data() const {
            return m_data;
        }
    };

    // Alias
    using Mat2i = Matrix<int,2,2>;
}

// Opérateurs externes
template<class I,size_t LIG,size_t COL>
math::Matrix<I,LIG,COL> operator * (I const& k, math::Matrix<I,LIG,COL> const& m) {
    return m * k;
}

// Macros JNI
#define MAT_CREATE(cls, type)                                                                   \
    extern "C" JNIEXPORT                                                                        \
    jlong JNICALL METH_NAME(cls, create)(JNIEnv*, jclass) {                                     \
        auto pt = std::make_shared<cls>();                                                      \
        pt->register_jni(true);                                                                 \
                                                                                                \
        return pt->get_jhandle();                                                               \
    }

#define MAT_CREATEA(cls, type)                                                                  \
    extern "C" JNIEXPORT                                                                        \
    jlong JNICALL METH_NAME(cls, createA)(JNIEnv* env, jclass, type ## Array factors) {         \
        jni::array<type ## Array> arr(env, factors);                                            \
        auto pt = std::make_shared<cls>(arr.data());                                            \
        pt->register_jni(true);                                                                 \
                                                                                                \
        return pt->get_jhandle();                                                               \
    }

#define MAT_CREATEM(cls, type)                                                                  \
    extern "C" JNIEXPORT                                                                        \
    jlong JNICALL METH_NAME(cls, createM)(JNIEnv* env, jclass, jobject jobj) {                  \
        auto pto = jni::fromJava<cls>(env, jobj);                                               \
        auto pt = std::make_shared<cls>(*pto);                                                  \
        pt->register_jni(true);                                                                 \
                                                                                                \
        return pt->get_jhandle();                                                               \
    }

#define MAT_GETDATA(cls, type)                                                                  \
    extern "C" JNIEXPORT                                                                        \
    type ## Array JNICALL METH_NAME(cls, getDataA)(JNIEnv* env, jobject jthis) {                \
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
                                                                                                \
        auto data = pt->data();                                                                 \
        jni::array<type ## Array> jarr(env, data.size());                                       \
        std::copy(data.begin(), data.end(), jarr.begin());                                      \
                                                                                                \
        return jarr;                                                                            \
    }

#define MAT_GETFACTOR(cls, type)                                                                \
    extern "C" JNIEXPORT                                                                        \
    type JNICALL METH_NAME(cls, getFactor)(JNIEnv* env, jobject jthis, jint c, jint l) {        \
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
        return (*pt)[math::P(c, l)];                                                            \
    }

#define MAT_SETFACTOR(cls, type)                                                                \
    extern "C" JNIEXPORT                                                                        \
    void JNICALL METH_NAME(cls, setFactor)(JNIEnv* env, jobject jthis, jint c, jint l, type v) {\
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
        (*pt)[math::P(c, l)] = v;                                                               \
    }

#define MAT_EQUAL(cls, type)                                                                    \
    extern "C" JNIEXPORT                                                                        \
    jboolean JNICALL METH_NAME(cls, equal)(JNIEnv* env, jobject jthis, jobject jobj) {          \
        auto ptt = jni::fromJava<cls>(env, jthis);                                              \
        auto pto = jni::fromJava<cls>(env, jobj);                                               \
                                                                                                \
        return jboolean((*ptt) == (*pto));                                                      \
    }

#define MAT_PLUSA(cls, type)                                                                    \
    extern "C" JNIEXPORT                                                                        \
    void JNICALL METH_NAME(cls, plusA)(JNIEnv* env, jobject jthis, jobject jobj) {              \
        auto ptt = jni::fromJava<cls>(env, jthis);                                              \
        auto pto = jni::fromJava<cls>(env, jobj);                                               \
                                                                                                \
        (*ptt) += (*pto);                                                                       \
    }

#define MAT_MINUSA(cls, type)                                                                   \
    extern "C" JNIEXPORT                                                                        \
    void JNICALL METH_NAME(cls, minusA)(JNIEnv* env, jobject jthis, jobject jobj) {             \
        auto ptt = jni::fromJava<cls>(env, jthis);                                              \
        auto pto = jni::fromJava<cls>(env, jobj);                                               \
                                                                                                \
        (*ptt) -= (*pto);                                                                       \
    }

#define MAT_TIMESA(cls, type)                                                                   \
    extern "C" JNIEXPORT                                                                        \
    void JNICALL METH_NAME(cls, timesA)(JNIEnv* env, jobject jthis, type k) {                   \
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
                                                                                                \
        (*pt) *= k;                                                                             \
    }

#define MAT_DIVA(cls, type)                                                                     \
    extern "C" JNIEXPORT                                                                        \
    void JNICALL METH_NAME(cls, divA)(JNIEnv* env, jobject jthis, type k) {                     \
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
                                                                                                \
        (*pt) /= k;                                                                             \
    }

#define MAT_JNI(cls, type)      \
    MAT_CREATE(cls, type)       \
    MAT_CREATEA(cls, type)      \
    MAT_CREATEM(cls, type)      \
    MAT_GETDATA(cls, type)      \
    MAT_GETFACTOR(cls, type)    \
    MAT_SETFACTOR(cls, type)    \
    MAT_EQUAL(cls, type)        \
    MAT_PLUSA(cls, type)        \
    MAT_MINUSA(cls, type)       \
    MAT_TIMESA(cls, type)       \
    MAT_DIVA(cls, type)