//
// Created by julien on 20/04/2019.
//
#pragma once

#include <algorithm>
#include <array>
#include <memory>
#include <type_traits>

#include "jnitools.h"
#include "macros.h"

namespace math {
    // Template
    template<class I, size_t DEG> class Vector: public jni::JNIClass {
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
        Vector() {
            for (size_t i = 0; i < DEG; ++i) {
                m_data[i] = 0;
            }
        };
        Vector(I factors[DEG]) {
            for (size_t i = 0; i < DEG; ++i) {
                m_data[i] = factors[i];
            }
        }
        Vector(Vector const& c): m_data(c.m_data) {};
        template<class... Args> explicit Vector(Args const&... v): m_data({v...}) {
            static_assert(sizeof...(Args) == DEG, "Need exactly DEG args");
        };

        // Opérateurs
        I&       operator [] (size_t i)       { return m_data[i]; }
        I const& operator [] (size_t i) const { return m_data[i]; }

        bool operator == (Vector const& c) const { return  std::equal(begin(), end(), c.begin()); }
        bool operator != (Vector const& c) const { return !std::equal(begin(), end(), c.begin()); }

        Vector operator + () const {
            Vector r;

            for (size_t i = 0; i < DEG; ++i) {
                r[i] = +m_data[i];
            }

            return r;
        }
        Vector operator - () const {
            Vector r;

            for (size_t i = 0; i < DEG; ++i) {
                r[i] = -m_data[i];
            }

            return r;
        }

        Vector& operator += (Vector const& c) {
            for (size_t i = 0; i < DEG; ++i) {
                m_data[i] += c[i];
            }

            return *this;
        }
        Vector& operator -= (Vector const& c) {
            for (size_t i = 0; i < DEG; ++i) {
                m_data[i] -= c[i];
            }

            return *this;
        }
        Vector& operator *= (I const& k) {
            for (size_t i = 0; i < DEG; ++i) {
                m_data[i] *= k;
            }

            return *this;
        }
        Vector& operator /= (I const& k) {
            for (size_t i = 0; i < DEG; ++i) {
                m_data[i] /= k;
            }

            return *this;
        }

        Vector operator + (Vector const& v) const {
            Vector r(*this); r += v; return r;
        }
        Vector operator - (Vector const& v) const {
            Vector r(*this); r -= v; return r;
        }
        I operator * (Vector<I,DEG> const& v) const {
            I r = 0;
            for (size_t i = 0; i < DEG; ++i) {
                r += m_data[i] * v[i];
            }

            return r;
        }
        Vector operator * (I const& k) const {
            Vector r(*this); r *= k;
            return r;
        }
        Vector operator / (I const& k) const {
            Vector r(*this); r /= k;
            return r;
        }

        // Méthodes
        size_t size() const {
            return DEG;
        }

        std::array<I,DEG> const& data() const {
            return m_data;
        }

        iterator       begin()       { return m_data.begin(); }
        const_iterator begin() const { return m_data.begin(); }
        iterator       end()         { return m_data.end();   }
        const_iterator end()   const { return m_data.end();   }
    };

    // Alias
    template<class I> using Vec2 = Vector<I,2>;
    template<class I> using Vec3 = Vector<I,3>;
    template<class I> using Vec4 = Vector<I,4>;

    using Vec2i = Vec2<int>;
    using Vec3i = Vec3<int>;
    using Vec4i = Vec4<int>;

    using Vec2f = Vec2<float>;
    using Vec3f = Vec3<float>;
    using Vec4f = Vec4<float>;

    // Outils
    template<class I> Vec3<I> cross(Vec3<I> const& v1, Vec3<I> const& v2) {
        return Vec3<I>(
                v1[1] * v2[2] - v1[2] * v2[1],
                v1[0] * v2[2] - v1[2] * v2[0],
                v1[0] * v2[1] - v1[1] * v2[0]
        );
    }
}

// Opérateurs externes
template<class I,size_t DEG>
math::Vector<I,DEG> operator * (I const& k, math::Vector<I,DEG> const& v) {
    return v * k;
}

// Macros JNI
#define VEC_CREATE(cls, type, ...)                                                              \
    extern "C" JNIEXPORT                                                                        \
    jlong JNICALL METH_NAME(cls, create)(JNIEnv*, jclass, TYPE_ARGS(type, __VA_ARGS__)) {       \
        auto pt = std::make_shared<cls>(ARGS(__VA_ARGS__));                                     \
        pt->register_jni(true);                                                                 \
                                                                                                \
        return pt->get_jhandle();                                                               \
    }

#define VEC_CREATEA(cls, type)                                                                  \
    extern "C" JNIEXPORT                                                                        \
    jlong JNICALL METH_NAME(cls, createA)(JNIEnv* env, jclass, type ## Array factors) {         \
        jni::array<type ## Array> arr(env, factors);                                            \
        auto pt = std::make_shared<cls>(arr.data());                                            \
        pt->register_jni(true);                                                                 \
                                                                                                \
        return pt->get_jhandle();                                                               \
    }

#define VEC_CREATEC(cls, type)                                                                  \
    extern "C" JNIEXPORT                                                                        \
    jlong JNICALL METH_NAME(cls, createC)(JNIEnv* env, jclass, jobject jobj) {                  \
        auto pto = jni::fromJava<cls>(env, jobj);                                               \
        auto pt = std::make_shared<cls>(*pto);                                                  \
        pt->register_jni(true);                                                                 \
                                                                                                \
        return pt->get_jhandle();                                                               \
    }

#define VEC_GETDATA(cls, type)                                                                  \
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

#define VEC_GETCOORD(cls, type)                                                                 \
    extern "C" JNIEXPORT                                                                        \
    type JNICALL METH_NAME(cls, getCoord)(JNIEnv* env, jobject jthis, jint i) {                 \
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
        return (*pt)[i];                                                                        \
    }

#define VEC_SETCOORD(cls, type)                                                                 \
    extern "C" JNIEXPORT                                                                        \
    void JNICALL METH_NAME(cls, setCoord)(JNIEnv* env, jobject jthis, jint i, type v) {         \
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
        (*pt)[i] = v;                                                                           \
    }

#define VEC_EQUAL(cls, type)                                                                    \
    extern "C" JNIEXPORT                                                                        \
    jboolean JNICALL METH_NAME(cls, equal)(JNIEnv* env, jobject jthis, jobject jobj) {          \
        auto ptt = jni::fromJava<cls>(env, jthis);                                              \
        auto pto = jni::fromJava<cls>(env, jobj);                                               \
                                                                                                \
        return (jboolean) ((*ptt) == (*pto));                                                   \
    }

#define VEC_TIMESV(cls, type)                                                                   \
    extern "C" JNIEXPORT                                                                        \
    type JNICALL METH_NAME(cls, timesV)(JNIEnv* env, jobject jthis, jobject jv) {               \
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
        auto ptv = jni::fromJava<cls>(env, jv);                                                 \
                                                                                                \
        return (*pt) * (*ptv);                                                                  \
    }

#define VECTOR_JNI(cls, type, ...)        \
    VEC_CREATE(  cls, type, __VA_ARGS__)  \
    VEC_CREATEA( cls, type)               \
    VEC_CREATEC( cls, type)               \
    VEC_GETDATA( cls, type)               \
    VEC_GETCOORD(cls, type)               \
    VEC_SETCOORD(cls, type)               \
    VEC_EQUAL(   cls, type)               \
    VEC_TIMESV(cls, type)