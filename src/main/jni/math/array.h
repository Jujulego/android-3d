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

        // Iterators
        class vector_iterator {
        public:
            // Alias
            using base_iterator = typename std::vector<vector_pointer>::iterator;

        private:
            // Attributes
            base_iterator m_it;

        public:
            // Alias
            using value_type        = vector_type;
            using reference         = vector_type&;
            using difference_type   = ptrdiff_t;
            using iterator_category = std::random_access_iterator_tag;

            // Constructors
            vector_iterator() = default;
            vector_iterator(base_iterator it): m_it(it) {}

            // Operators
            // - deference
            reference      operator *  () const { return *(*m_it); }
            vector_pointer operator -> () const { return *m_it; }
            reference      operator [](size_t i) const { return *(m_it[i]); }

            // - compare
            bool operator <  (vector_iterator const& vit) const { return m_it <  vit.m_it; }
            bool operator <= (vector_iterator const& vit) const { return m_it <= vit.m_it; }
            bool operator == (vector_iterator const& vit) const { return m_it == vit.m_it; }
            bool operator != (vector_iterator const& vit) const { return m_it != vit.m_it; }
            bool operator >= (vector_iterator const& vit) const { return m_it >= vit.m_it; }
            bool operator >  (vector_iterator const& vit) const { return m_it >  vit.m_it; }

            // - increment
            vector_iterator& operator ++ () { ++m_it; return *this; }
            vector_iterator& operator -- () { --m_it; return *this; }

            vector_iterator operator ++ (int) {
                vector_iterator tmp(*this);
                ++(*this);
                return tmp;
            }

            vector_iterator operator -- (int) {
                vector_iterator tmp(*this);
                --(*this);
                return tmp;
            }

            // - assign
            vector_iterator& operator += (size_t v) { m_it += v; return *this; }
            vector_iterator& operator -= (size_t v) { m_it -= v; return *this; }

            // - compute
            vector_iterator operator + (size_t v) const { vector_iterator r(*this); r += v; return r; }
            vector_iterator operator - (size_t v) const { vector_iterator r(*this); r -= v; return r; }

            friend vector_iterator operator + (size_t v, vector_iterator const& vit) { vector_iterator r(vit); r += v; return r; }

            difference_type operator - (vector_iterator vit) const {
                return (m_it - vit.m_it);
            }

            // Methods
            vector_pointer&       get()       { return *m_it; }
            vector_pointer const& get() const { return *m_it; }

            base_iterator const& it() const { return m_it; }
        };
        class const_vector_iterator {
        public:
            // Alias
            using base_iterator = typename std::vector<vector_pointer>::const_iterator;

        private:
            // Attributes
            base_iterator m_it;

        public:
            // Alias
            using value_type        = vector_type;
            using reference         = vector_type const&;
            using difference_type   = ptrdiff_t;
            using iterator_category = std::random_access_iterator_tag;

            // Constructors
            const_vector_iterator() = default;
            const_vector_iterator(base_iterator it): m_it(it) {}
            const_vector_iterator(vector_iterator const& vit): m_it(vit.it()) {}

            // Operators
            // - deference
            reference      operator *  () const { return *(*m_it); }
            vector_const_pointer operator -> () const { return *m_it; }
            reference      operator [](size_t i) const { return *(m_it[i]); }

            // - compare
            bool operator <  (const_vector_iterator const& vit) const { return m_it <  vit.m_it; }
            bool operator <= (const_vector_iterator const& vit) const { return m_it <= vit.m_it; }
            bool operator == (const_vector_iterator const& vit) const { return m_it == vit.m_it; }
            bool operator != (const_vector_iterator const& vit) const { return m_it != vit.m_it; }
            bool operator >= (const_vector_iterator const& vit) const { return m_it >= vit.m_it; }
            bool operator >  (const_vector_iterator const& vit) const { return m_it >  vit.m_it; }

            // - increment
            const_vector_iterator& operator ++ () { ++m_it; return *this; }
            const_vector_iterator& operator -- () { --m_it; return *this; }

            const_vector_iterator operator ++ (int) {
                const_vector_iterator tmp(*this);
                ++(*this);
                return tmp;
            }

            const_vector_iterator operator -- (int) {
                const_vector_iterator tmp(*this);
                --(*this);
                return tmp;
            }

            // - assign
            const_vector_iterator& operator += (size_t v) { m_it += v; return *this; }
            const_vector_iterator& operator -= (size_t v) { m_it -= v; return *this; }

            // - compute
            const_vector_iterator operator + (size_t v) const { const_vector_iterator r(*this); r += v; return r; }
            const_vector_iterator operator - (size_t v) const { const_vector_iterator r(*this); r -= v; return r; }

            friend const_vector_iterator operator + (size_t v, const_vector_iterator const& vit) {
                const_vector_iterator r(vit);
                r += v;

                return r;
            }

            difference_type operator - (const_vector_iterator vit) const {
                return (m_it - vit.m_it);
            }

            // Methods
            vector_const_pointer&       get()       { return *m_it; }
            vector_const_pointer const& get() const { return *m_it; }

            base_iterator const& it() const { return m_it; }
        };

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
        using iterator        = vector_iterator;
        using const_iterator  = const_vector_iterator;
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

        void erase(const_iterator const& it) {
            m_data.erase(it.it());
        }

        size_t size() const {
            return m_data.size();
        }

        iterator       begin()       { return vector_iterator(m_data.begin());       }
        const_iterator begin() const { return const_vector_iterator(m_data.begin()); }
        iterator       end()         { return vector_iterator(m_data.end());         }
        const_iterator end()   const { return const_vector_iterator(m_data.end());   }
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
        if (!ptr->is_registred()) ptr->register_jni();                          \
        ptr->acquire();                                                         \
                                                                                \
        return ptr->get_jhandle();                                              \
    }

#define VECARR_SET(cls, vec)                                                                \
    extern "C" JNIEXPORT                                                                    \
    void JNICALL METH_NAME(cls, nset)(JNIEnv* env, jobject jthis, jint i, jobject jobj) {   \
        auto pt = jni::fromJava<cls>(env, jthis);                                           \
        auto ptv = jni::fromJava<vec>(env, jobj);                                           \
                                                                                            \
        pt->get(i) = ptv;                                                                   \
    }

#define VECARR_ADD(cls, vec)                                                            \
    extern "C" JNIEXPORT                                                                \
    jboolean JNICALL METH_NAME(cls, nadd)(JNIEnv* env, jobject jthis, jobject jobj) {   \
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

#define VECARR_FIND(cls, vec)                                                   \
    extern "C" JNIEXPORT                                                        \
    jint JNICALL METH_NAME(cls, find)(JNIEnv* env, jobject jthis, jobject jv) { \
        auto pt = jni::fromJava<cls>(env, jthis);                               \
        auto ptv = jni::fromJava<vec>(env, jv);                                 \
                                                                                \
        auto it = std::find(pt->begin(), pt->end(), *ptv);                      \
                                                                                \
        if (it == pt->end()) return -1;                                         \
        return it - pt->begin();                                                \
    }

#define VECARR_REMOVE(cls, vec)                                                        \
    extern "C" JNIEXPORT                                                               \
    jboolean JNICALL METH_NAME(cls, nremove)(JNIEnv* env, jobject jthis, jobject jv) { \
        auto pt = jni::fromJava<cls>(env, jthis);                                      \
        auto ptv = jni::fromJava<vec>(env, jv);                                        \
                                                                                       \
        auto it = std::find(pt->begin(), pt->end(), *ptv);                             \
                                                                                       \
        if (it == pt->end()) return false;                                             \
        pt->erase(it);                                                                 \
                                                                                       \
        return true;                                                                   \
    }

#define VECTOR_ARRAY_JNI(cls, vec, type)    \
    VECARR_CREATE(cls)                      \
    VECARR_GET(cls)                         \
    VECARR_SET(cls, vec)                    \
    VECARR_ADD(cls, vec)                    \
    VECARR_GETSIZE(cls)                     \
    VECARR_FIND(cls, vec)                   \
    VECARR_REMOVE(cls, vec)