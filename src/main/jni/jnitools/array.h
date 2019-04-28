//
// Created by julien on 28/04/2019.
//
#pragma once

#include <jni.h>
#include <list>

#include "meta.h"

// Macros
#define JNIARRAY(type, jname)                                                           \
    template<> class array<type ## Array> {                                             \
    private:                                                                            \
        JNIEnv* m_env;                                                                  \
        type ## Array m_ref;                                                            \
        type* m_data;                                                                   \
                                                                                        \
    public:                                                                             \
        using value_type      = type;                                                   \
        using reference       = type&;                                                  \
        using const_reference = type const&;                                            \
        using pointer         = type*;                                                  \
        using const_pointer   = type const*;                                            \
        using iterator        = type*;                                                  \
        using const_iterator  = type const*;                                            \
        using size_type       = jsize;                                                  \
        using difference_type = ptrdiff_t;                                              \
                                                                                        \
        array(JNIEnv* env, type ## Array ref): m_env(env), m_ref(ref) {                 \
            m_data = m_env->Get##jname##ArrayElements(m_ref, nullptr);                  \
        }                                                                               \
                                                                                        \
        array(JNIEnv* env, jsize size): array(env, env->New##jname##Array(size)) {}     \
                                                                                        \
        ~array() {                                                                      \
            m_env->Release##jname##ArrayElements(m_ref, m_data, 0);                     \
        }                                                                               \
                                                                                        \
        operator type ## Array () { return m_ref; }                                     \
                                                                                        \
        type&       operator [] (size_t i)       { return m_data[i]; }                  \
        type const& operator [] (size_t i) const { return m_data[i]; }                  \
                                                                                        \
        jsize size() const {                                                            \
            return m_env->GetArrayLength(m_ref);                                        \
        }                                                                               \
                                                                                        \
        type*       begin()       { return m_data; }                                    \
        type const* begin() const { return m_data; }                                    \
        type*       end()         { return m_data + size() * sizeof(jint); }            \
        type const* end()   const { return m_data + size() * sizeof(jint); }            \
                                                                                        \
        type*       data()       { return m_data; }                                     \
        type const* data() const { return m_data; }                                     \
    }

namespace jni {
    // Templates
    template<class A> struct array {
        static_assert(is_jarray<A>::value, "A need to be a subclass of jarray");
    };

    JNIARRAY(jboolean, Boolean);
    JNIARRAY(jchar,    Char);
    JNIARRAY(jbyte,    Byte);
    JNIARRAY(jshort,   Short);
    JNIARRAY(jint,     Int);
    JNIARRAY(jlong,    Long);
    JNIARRAY(jfloat,   Float);
    JNIARRAY(jdouble,  Double);
}