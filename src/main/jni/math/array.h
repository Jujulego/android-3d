//
// Created by julien on 03/07/2019.
//
#pragma once

#include <algorithm>
#include <memory>
#include <type_traits>
#include <vector>

#include "jnitools.h"
#include "macros.h"
#include "vector.h"

namespace math {
    // Template
    template<class I, size_t DEG> class VectorArray: public jni::JNIClass {
        static_assert(DEG >= 2, "DEG should be at least 2");
        static_assert(std::is_arithmetic<I>::value, "I must be an arithmetic type");

    public:
        // Alias
        using vector_type          = Vector<I,DEG>;
        using vector_pointer       = std::shared_ptr<vector_type>;
        using vector_const_pointer = std::shared_ptr<const vector_type>;

    private:
        // Attributes
        std::vector<vector_pointer> m_data;

    public:
        // Alias
        using value_type      = vector_type;
        using reference       = vector_type&;
        using const_reference = vector_type const&;
        using pointer         = vector_pointer;
        using const_pointer   = vector_const_pointer;
        // using iterator        = typename std::vector<vector_type>::iterator;
        // using const_iterator  = typename std::vector<vector_type>::const_iterator;
        using size_type       = size_t;
        using difference_type = ptrdiff_t;

        // Constructors
        VectorArray() = default;
        VectorArray(size_t n): m_data(n, nullptr) {
            for (size_t i = 0; i < n; ++i) {
                m_data[i] = std::make_shared<vector_type>();
            }
        }

        // Operators
        vector_type&       operator [] (size_t i)       { return *get(i); }
        vector_type const& operator [] (size_t i) const { return *get(i); }

        // Methods
        vector_pointer&       get(size_t i)       { return m_data[i]; }
        vector_pointer const& get(size_t i) const { return m_data[i]; }

        void push(vector_pointer const& vec) {
            m_data.push_back(vec);
        }

        size_t size() const {
            return m_data.size();
        }

        // iterator       begin()       { return m_data.begin(); }
        // const_iterator begin() const { return m_data.begin(); }
        // iterator       end()         { return m_data.end();   }
        // const_iterator end()   const { return m_data.end();   }
    };

    // Alias
    template<class I> using VectorArray2 = VectorArray<I,2>;
    template<class I> using VectorArray3 = VectorArray<I,3>;
    template<class I> using VectorArray4 = VectorArray<I,4>;

    using VectorArray2i = VectorArray2<int>;
    using VectorArray3i = VectorArray3<int>;
    using VectorArray4i = VectorArray4<int>;

    using VectorArray2f = VectorArray2<float>;
    using VectorArray3f = VectorArray3<float>;
    using VectorArray4f = VectorArray4<float>;
}

// Macros JNI
#define VECARR_CREATE(cls)                                              \
    extern "C" JNIEXPORT                                                \
    jlong JNICALL METH_NAME(cls, create)(JNIEnv*, jclass, jint n) {     \
        auto pt = std::make_shared<cls>(n);                             \
        pt->register_jni(true);                                         \
                                                                        \
        return pt->get_jhandle();                                       \
    }

#define VECARR_GET(cls)                                                         \
    extern "C" JNIEXPORT                                                        \
    jlong JNICALL METH_NAME(cls, nget)(JNIEnv* env, jobject jthis, jint i) {    \
        auto pt = jni::fromJava<cls>(env, jthis);                               \
                                                                                \
        if (i >= pt->size()) {                                                  \
            jni::javaThrow(env, "java/lang/ArrayIndexOutOfBoundsException", "i out of bounds"); \
            return 0;                                                           \
        }                                                                       \
                                                                                \
        auto ptr = pt->get(i);                                                  \
                                                                                \
        jlong handle = ptr->get_jhandle();                                      \
        if (handle == INVALID_HANDLE) ptr->register_jni();                      \
        ptr->acquire();                                                         \
                                                                                \
        return ptr->get_jhandle();                                              \
    }

#define VECARR_SET(cls, vec)                                                                \
    extern "C" JNIEXPORT                                                                    \
    void JNICALL METH_NAME(cls, set)(JNIEnv* env, jobject jthis, jint i, jobject jobj) {    \
        auto pt = jni::fromJava<cls>(env, jthis);                                           \
        auto ptv = jni::fromJava<vec>(env, jobj);                                           \
                                                                                            \
        pt->get(i) = ptv;                                                                   \
    }

#define VECARR_ADD(cls, vec)                                                            \
    extern "C" JNIEXPORT                                                                \
    jboolean JNICALL METH_NAME(cls, add)(JNIEnv* env, jobject jthis, jobject jobj) {    \
        auto pt = jni::fromJava<cls>(env, jthis);                                       \
        auto ptv = jni::fromJava<vec>(env, jobj);                                       \
                                                                                        \
        pt->push(ptv);                                                                  \
                                                                                        \
        return true;                                                                    \
    }

#define VECARR_GETSIZE(cls)                                             \
    extern "C" JNIEXPORT                                                \
    jint JNICALL METH_NAME(cls, getSize)(JNIEnv* env, jobject jthis) {  \
        auto pt = jni::fromJava<cls>(env, jthis);                       \
                                                                        \
        return pt->size();                                              \
    }

#define VECTOR_ARRAY_JNI(cls, vec, type)    \
    VECARR_CREATE(cls)                      \
    VECARR_GET(cls)                         \
    VECARR_SET(cls, vec)                    \
    VECARR_ADD(cls, vec)                    \
    VECARR_GETSIZE(cls)