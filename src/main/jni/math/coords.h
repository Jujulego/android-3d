//
// Created by julien on 20/04/2019.
//
#pragma once

#include <memory>
#include <type_traits>

#include "jnitools.h"
#include "macros.h"

namespace math {
    // Template
    template<class I, size_t DEG, bool PT> struct Coords: jni::JNIClass {
        static_assert(DEG >= 2, "DEG should be at least 2");
        static_assert(std::is_arithmetic<I>::value, "I must be an arithmetic type");
    };
};

// Macros JNI
#define COORD_CREATE(cls, type, ...)                                                            \
    extern "C" JNIEXPORT                                                                        \
    jlong JNICALL METH_NAME(cls, create)(JNIEnv*, jclass, TYPE_ARGS(type, __VA_ARGS__)) {       \
        auto pt = std::make_shared<cls>(ARGS(__VA_ARGS__));                                     \
        pt->register_jni(true);                                                                 \
                                                                                                \
        return pt->get_jhandle();                                                               \
    }

#define COORD_GETCOORD(cls, type)                                                               \
    extern "C" JNIEXPORT                                                                        \
    type JNICALL METH_NAME(cls, getCoord)(JNIEnv* env, jobject jthis, jint i) {                 \
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
        return (*pt)[i];                                                                        \
    }

#define COORD_SETCOORD(cls, type)                                                               \
    extern "C" JNIEXPORT                                                                        \
    void JNICALL METH_NAME(cls, setCoord)(JNIEnv* env, jobject jthis, jint i, jint v) {         \
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
        (*pt)[i] = v;                                                                           \
    }

#define COORD_EQUAL(cls, type)                                                                  \
    extern "C" JNIEXPORT                                                                        \
    jboolean JNICALL METH_NAME(cls, equal)(JNIEnv* env, jobject jthis, jobject jobj) {          \
        auto ptt = jni::fromJava<cls>(env, jthis);                                              \
        auto pto = jni::fromJava<cls>(env, jobj);                                               \
                                                                                                \
        return (jboolean) ((*ptt) == (*pto));                                                   \
    }

#define COORD_UPLUS(cls, type)                                                                  \
    extern "C" JNIEXPORT                                                                        \
    jlong JNICALL METH_NAME(cls, plus)(JNIEnv* env, jobject jthis) {                            \
        auto opt = jni::fromJava<cls>(env, jthis);                                              \
        auto pt = std::make_shared<cls>(+(*opt));                                               \
        pt->register_jni(true);                                                                 \
                                                                                                \
        return pt->get_jhandle();                                                               \
    }

#define COORD_APLUS(cls, type)                                                                  \
    extern "C" JNIEXPORT                                                                        \
    void JNICALL METH_NAME(cls, plusA)(JNIEnv* env, jobject jthis, jobject jobj) {              \
        auto ptt = jni::fromJava<cls>(env, jthis);                                              \
        auto pto = jni::fromJava<cls>(env, jobj);                                               \
                                                                                                \
        (*ptt) += (*pto);                                                                       \
    }

#define COORD_UMINUS(cls, type)                                                                 \
    extern "C" JNIEXPORT                                                                        \
    jlong JNICALL METH_NAME(cls, minus)(JNIEnv* env, jobject jthis) {                           \
        auto opt = jni::fromJava<cls>(env, jthis);                                              \
        auto pt = std::make_shared<cls>(-(*opt));                                               \
        pt->register_jni(true);                                                                 \
                                                                                                \
        return pt->get_jhandle();                                                               \
    }

#define COORD_AMINUS(cls, type)                                                                 \
    extern "C" JNIEXPORT                                                                        \
    void JNICALL METH_NAME(cls, minusA)(JNIEnv* env, jobject jthis, jobject jobj) {             \
        auto ptt = jni::fromJava<cls>(env, jthis);                                              \
        auto pto = jni::fromJava<cls>(env, jobj);                                               \
                                                                                                \
        (*ptt) -= (*pto);                                                                       \
    }
