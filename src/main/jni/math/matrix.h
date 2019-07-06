//
// Created by julien on 22/04/2019.
//
#pragma once

#include <algorithm>
#include <array>
#include <cmath>
#include <type_traits>

#include "jnitools.h"
#include "macros.h"
#include "vector.h"
#include "outils.h"

namespace math {
    // Alias
    using P = std::pair<size_t,size_t>;

    // Classe
    template<class I,size_t LIG,size_t COL> class Matrix: public jni::JNIClass {
        static_assert(LIG >= 2, "LIG should be at least 2");
        static_assert(COL >= 2, "COL should be at least 2");
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

        Matrix(I factors[LIG*COL]) {
            for (size_t l = 0; l < LIG; ++l) {
                for (size_t c = 0; c < COL; ++c) {
                    m_data[to_index(l, c)] = factors[c + (l * COL)];
                }
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

        Vector<I,LIG> operator * (Vector<I,COL> const& v) const {
            Vector<I,LIG> r;

            for (size_t l = 0; l < LIG; ++l) {
                r[l] = lig(l) * v;
            }

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

        std::array<I,LIG*COL> const& data() const {
            return m_data;
        }
    };

    // Alias
    template<class I> using Mat2 = Matrix<I,2,2>;
    template<class I> using Mat3 = Matrix<I,3,3>;
    template<class I> using Mat4 = Matrix<I,4,4>;

    using Mat2i = Mat2<int>;
    using Mat3i = Mat3<int>;
    using Mat4i = Mat4<int>;

    using Mat2f = Mat2<float>;
    using Mat3f = Mat3<float>;
    using Mat4f = Mat4<float>;

    // Outils
    namespace matrix {
        template<class I> Mat4<I>& scale(Mat4<I>& mat, I fx, I fy, I fz) {
            mat[P(0,0)] *= fx;
            mat[P(1,1)] *= fy;
            mat[P(2,2)] *= fz;

            return mat;
        }
        template<class I> Mat4<I>& scale(Mat4<I>& mat, Vec3<I> const& f) {
            return scale(mat, f[0], f[1], f[2]);
        }

        template<class I> Mat4<I>& translate(Mat4<I>& mat, I dx, I dy, I dz) {
            for (int l = 0; l < 4; ++l) {
                mat[P(l,3)] += mat[P(l,0)] * dx + mat[P(l,1)] * dy + mat[P(l,2)] * dz;
            }

            return mat;
        }
        template<class I> Mat4<I>& translate(Mat4<I>& mat, Vec3<I> const& d) {
            return translate(mat, d[0], d[1], d[2]);
        }

        template<class I> Mat4<I> rotate(double a, I x, I y, I z) {
            Mat4<I> mat;
            mat[P(3, 3)] = 1;

            a *= (M_PI / 180);
            double s = sin(a);
            double c = cos(a);

            if (x == 1 && y == 0 && z == 0) {
                mat[P(0,0)] = 1;
                mat[P(1,1)] = c; mat[P(2,2)] =  c;
                mat[P(2,1)] = s; mat[P(1,2)] = -s;
            } else if (x == 0 && y == 1 && z == 0) {
                mat[P(1,1)] = 1;
                mat[P(0,0)] = c; mat[P(2,2)] =  c;
                mat[P(0,2)] = s; mat[P(2,0)] = -s;
            } else if (x == 0 && y == 0 && z == 1) {
                mat[P(2,2)] = 1;
                mat[P(0,0)] = c; mat[P(1,1)] =  c;
                mat[P(0,1)] = s; mat[P(1,0)] = -s;
            } else {
                double len = length(x, y, z);
                if (len == 1) {
                    double r = 1 / len;
                    x *= r;
                    y *= r;
                    z *= r;
                }

                I xy = x * y;
                I yz = y * z;
                I zx = z * x;

                double nc = 1 - c;
                double xs = x * s;
                double ys = y * s;
                double zs = z * s;

                mat[P(0,0)] = x*x*nc +  c;
                mat[P(0,1)] =  xy*nc - zs;
                mat[P(0,2)] =  zx*nc + ys;
                mat[P(1,0)] =  xy*nc + zs;
                mat[P(1,1)] = y*y*nc +  c;
                mat[P(1,2)] =  yz*nc - xs;
                mat[P(2,0)] =  zx*nc - ys;
                mat[P(2,1)] =  yz*nc + xs;
                mat[P(2,2)] = z*z*nc +  c;
            }

            return mat;
        }
        template<class I> Mat4<I> rotate(double a, Vec3<I> const& axe) {
            return rotate(a, axe[0], axe[1], axe[2]);
        }

        template<class I> Mat4<I> lookAt(Vec3<I> const& eye, Vec3<I> const& center, Vec3<I> const& up) {
            // Compute f
            Vec3<I> f = center - eye;
            f /= f.length();

            // Compute s
            Vec3<I> s = cross(f, up);
            s /= s.length();

            // Compute u
            Vec3<I> u = cross(s, f);

            // Compute m
            Mat4<I> m;
            m[P(0,0)] =  s[0];  m[P(0,1)] =  s[1];  m[P(0,2)] =  s[2];
            m[P(1,0)] =  u[0];  m[P(1,1)] =  u[1];  m[P(1,2)] =  u[2];
            m[P(2,0)] = -f[0];  m[P(2,1)] = -f[1];  m[P(2,2)] = -f[2];
                                                                        m[P(3,3)] = 1;

            return translate(m, -eye);
        }
    }
}

// Opérateurs externes
template<class I,size_t LIG,size_t COL>
math::Matrix<I,LIG,COL> operator * (I const& k, math::Matrix<I,LIG,COL> const& m) {
    return m * k;
}

template<class I, size_t LIG, size_t COL>
math::Vector<I,COL> operator * (math::Vector<I,LIG> const& v, math::Matrix<I,LIG,COL> const& m) {
    math::Vector<I,COL> r;

    for (size_t c = 0; c < COL; ++c) {
        r[c] = v * m.col(c);
    }

    return r;
}

template<class I, size_t DEG>
math::Vector<I,DEG>& operator *= (math::Vector<I,DEG>& v, math::Matrix<I,DEG,DEG> const& m) {
    math::Vector<I,DEG> tmp(v);

    for (size_t c = 0; c < DEG; ++c) {
        v[c] = tmp * m.col(c);
    }

    return v;
}

template<class I, size_t L, size_t LC, size_t C>
math::Matrix<I,L,C> operator * (math::Matrix<I,L,LC> const& m1, math::Matrix<I,LC,C> const& m2) {
    math::Matrix<I,L,C> res;

    for (size_t l = 0; l < L; ++l) {
        for (size_t c = 0; c < C; ++c) {
            res[math::P(l,c)] = m1.lig(l) * m2.col(c);
        }
    }

    return res;
}

template<class I,size_t DEG>
math::Matrix<I,DEG,DEG>& operator *= (math::Matrix<I,DEG,DEG>& m1, math::Matrix<I,DEG,DEG> const& m2) {
    math::Matrix<I,DEG,DEG> tmp(m1);

    for (size_t l = 0; l < DEG; ++l) {
        for (size_t c = 0; c < DEG; ++c) {
            m1[math::P(l,c)] = tmp.lig(l) * m2.col(c);
        }
    }

    return m1;
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
    type ## Array JNICALL METH_NAME(cls, getData)(JNIEnv* env, jobject jthis) {                 \
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
    type JNICALL METH_NAME(cls, getFactor)(JNIEnv* env, jobject jthis, jint l, jint c) {        \
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
        return (*pt)[math::P(l,c)];                                                            \
    }

#define MAT_SETFACTOR(cls, type)                                                                \
    extern "C" JNIEXPORT                                                                        \
    void JNICALL METH_NAME(cls, setFactor)(JNIEnv* env, jobject jthis, jint l, jint c, type v) {\
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
        (*pt)[math::P(l,c)] = v;                                                               \
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

#define MAT_TIMESM(cls, type)                                                                   \
    extern "C" JNIEXPORT                                                                        \
    jobject JNICALL METH_NAME(cls, timesM)(JNIEnv* env, jobject jthis, jobject jmat) {          \
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
        auto ptm = jni::fromJava<cls>(env, jmat);                                               \
                                                                                                \
        auto ptr = std::make_shared<cls>((*pt) * (*ptm));                                       \
        ptr->register_jni(true);                                                                \
                                                                                                \
        return jni::toJava(env, *ptr);                                                          \
    }

#define MAT_TIMESMA(cls, type)                                                                  \
    extern "C" JNIEXPORT                                                                        \
    void JNICALL METH_NAME(cls, timesMA)(JNIEnv* env, jobject jthis, jobject jmat) {            \
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
        auto ptm = jni::fromJava<cls>(env, jmat);                                               \
                                                                                                \
        (*pt) *= (*ptm);                                                                        \
    }

#define MAT_TIMESV(cls, vec, type)                                                              \
    extern "C" JNIEXPORT                                                                        \
    jobject JNICALL METH_NAME(cls, timesV)(JNIEnv* env, jobject jthis, jobject jv) {            \
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
        auto ptv = jni::fromJava<vec>(env, jv);                                                 \
                                                                                                \
        auto ptr = std::make_shared<vec>((*pt) * (*ptv));                                       \
        ptr->register_jni(true);                                                                \
                                                                                                \
        return jni::toJava(env, *ptr);                                                          \
    }

#define VEC_TIMESMA(cls, mat, type)                                                             \
    extern "C" JNIEXPORT                                                                        \
    void JNICALL METH_NAME(cls, timesMA)(JNIEnv* env, jobject jthis, jobject jm) {              \
        auto ptv = jni::fromJava<cls>(env, jthis);                                              \
        auto ptm = jni::fromJava<mat>(env, jm);                                                 \
                                                                                                \
        (*ptv) *= (*ptm);                                                                       \
    }

#define MAT_DIVA(cls, type)                                                                     \
    extern "C" JNIEXPORT                                                                        \
    void JNICALL METH_NAME(cls, divA)(JNIEnv* env, jobject jthis, type k) {                     \
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
                                                                                                \
        (*pt) /= k;                                                                             \
    }

#define MAT_CONVERT(cls)                                                                            \
    template<> jobject jni::toJava<cls,jobject>(JNIEnv* env, cls const& obj) {                      \
        return jni::construct(env, "net/capellari/julien/threed/" #cls, "(J)V", obj.get_jhandle()); \
    }

#define MAT_JNI(cls, vec, type)     \
    MAT_CONVERT(  cls)              \
    MAT_CREATE(   cls, type)        \
    MAT_CREATEA(  cls, type)        \
    MAT_CREATEM(  cls, type)        \
    MAT_GETDATA(  cls, type)        \
    MAT_GETFACTOR(cls, type)        \
    MAT_SETFACTOR(cls, type)        \
    MAT_EQUAL(    cls, type)        \
    MAT_PLUSA(    cls, type)        \
    MAT_MINUSA(   cls, type)        \
    MAT_TIMESA(   cls, type)        \
    MAT_TIMESM(   cls, type)        \
    MAT_TIMESMA(  cls, type)        \
    MAT_TIMESV(   cls, vec, type)   \
    VEC_TIMESMA(  vec, cls, type)   \
    MAT_DIVA(     cls, type)