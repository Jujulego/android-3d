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
        std::vector<vector_type> m_data;

    public:
        // Alias
        using value_type      = vector_type;
        using reference       = vector_type&;
        using const_reference = vector_type const&;
        using pointer         = vector_pointer;
        using const_pointer   = vector_const_pointer;
        using iterator        = typename std::vector<vector_type>::iterator;
        using const_iterator  = typename std::vector<vector_type>::const_iterator;
        using size_type       = size_t;
        using difference_type = ptrdiff_t;

        // Constructors
        VectorArray() {}
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
#define VECARR_CREATE(cls)                                      \
    extern "C" JNIEXPORT                                        \
    jlong JNICALL METH_NAME(cls, create)(JNIEnv*, jclass) {     \
        auto pt = std::make_shared<cls>();                      \
        pt->register_jni(true);                                 \
                                                                \
        return pt->get_jhandle();                               \
    }

#define VECTOR_ARRAY_JNI(cls, type)   \
    VECARR_CREATE(cls)