//
// Created by julien on 20/04/2019.
//
#include "point.h"

#include <memory>

#include "jnitools.h"
#include "macros.h"
#include "macros/cat.h"

using namespace math;

// Macros
#define ARG(n, f) WHEN(NOT(f))(,) n
#define ARG_TYPE(t) PRIMITIVE_CAT(ARG_TYPE_, t)
#define ARG_TYPE_jint(n, f) WHEN(NOT(f))(,) jint n
#define ARG_TYPE_jfloat(n, f) WHEN(NOT(f))(,) jfloat n

#define ARGS(x, ...) FOR_EACH(ARG, x, __VA_ARGS__)
#define TYPE_ARGS(type, x, ...) ARGS(FOR_EACH(ARG_TYPE(type), x, __VA_ARGS__))

#define METH_NAME(cls, name) Java_net_capellari_julien_threed_##cls##_##name

#define POINT_CREATE(cls, type, ...)                                                            \
    extern "C" JNIEXPORT                                                                        \
    jlong JNICALL METH_NAME(cls, create)(JNIEnv*, jclass, TYPE_ARGS(type, __VA_ARGS__)) {       \
        auto pt = std::make_shared<cls>(ARGS(__VA_ARGS__));                                     \
        pt->register_jni(true);                                                                 \
                                                                                                \
        return pt->get_jhandle();                                                               \
    }

#define POINT_GETCOORD(cls, type)                                                               \
    extern "C" JNIEXPORT                                                                        \
    type JNICALL METH_NAME(cls, getCoord)(JNIEnv* env, jobject jthis, jint i) {                 \
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
        return (*pt)[i];                                                                        \
    }

#define POINT_SETCOORD(cls, type)                                                               \
    extern "C" JNIEXPORT                                                                        \
    void JNICALL METH_NAME(cls, setCoord)(JNIEnv* env, jobject jthis, jint i, jint v) {         \
        auto pt = jni::fromJava<cls>(env, jthis);                                               \
        (*pt)[i] = v;                                                                           \
    }

#define POINT_EQUAL(cls, type)                                                                  \
    extern "C" JNIEXPORT                                                                        \
    jboolean JNICALL METH_NAME(cls, equal)(JNIEnv* env, jobject jthis, jobject jobj) {          \
        auto ptt = jni::fromJava<cls>(env, jthis);                                              \
        auto pto = jni::fromJava<cls>(env, jobj);                                               \
                                                                                                \
        return (jboolean) ((*ptt) == (*pto));                                                   \
    }

#define POINT_PLUS(cls, type)                                                                   \
    extern "C" JNIEXPORT                                                                        \
    jlong JNICALL METH_NAME(cls, plus)(JNIEnv* env, jobject jthis) {                            \
        auto opt = jni::fromJava<cls>(env, jthis);                                              \
        auto pt = std::make_shared<cls>(+(*opt));                                               \
        pt->register_jni(true);                                                                 \
                                                                                                \
        return pt->get_jhandle();                                                               \
    }

#define POINT_PLUSA(cls, type)                                                                  \
    extern "C" JNIEXPORT                                                                        \
    void JNICALL METH_NAME(cls, plusA)(JNIEnv* env, jobject jthis, jobject jobj) {              \
        auto ptt = jni::fromJava<cls>(env, jthis);                                              \
        auto pto = jni::fromJava<cls>(env, jobj);                                               \
                                                                                                \
        (*ptt) += (*pto);                                                                       \
    }

#define POINT_MINUS(cls, type)                                                                  \
    extern "C" JNIEXPORT                                                                        \
    jlong JNICALL METH_NAME(cls, minus)(JNIEnv* env, jobject jthis) {                           \
        auto opt = jni::fromJava<cls>(env, jthis);                                              \
        auto pt = std::make_shared<cls>(-(*opt));                                               \
        pt->register_jni(true);                                                                 \
                                                                                                \
        return pt->get_jhandle();                                                               \
    }

#define POINT_MINUSA(cls, type)                                                                 \
    extern "C" JNIEXPORT                                                                        \
    void JNICALL METH_NAME(cls, minusA)(JNIEnv* env, jobject jthis, jobject jobj) {             \
        auto ptt = jni::fromJava<cls>(env, jthis);                                              \
        auto pto = jni::fromJava<cls>(env, jobj);                                               \
                                                                                                \
        (*ptt) -= (*pto);                                                                       \
    }

#define POINT_JNI(cls, type, ...)           \
    POINT_CREATE(  cls, type, __VA_ARGS__)  \
    POINT_GETCOORD(cls, type)               \
    POINT_SETCOORD(cls, type)               \
    POINT_EQUAL(   cls, type)               \
    POINT_PLUS(    cls, type)               \
    POINT_PLUSA(   cls, type)               \
    POINT_MINUS(   cls, type)               \
    POINT_MINUSA(  cls, type)

// JNI
POINT_JNI(Point2i, jint, x, y)