//
// Created by julien on 20/04/2019.
//
#include <jni.h>

#include "vector.h"
#include "macros.h"

using namespace math;

// JNI
VECTOR_JNI(Vec2i, jint, x, y)
VECTOR_JNI(Vec3i, jint, x, y, z)
VECTOR_JNI(Vec4i, jint, x, y, z, a)

VECTOR_JNI(Vec2f, jfloat, x, y)
VECTOR_JNI(Vec3f, jfloat, x, y, z)
VECTOR_JNI(Vec4f, jfloat, x, y, z, a)

extern "C" JNIEXPORT
long JNICALL METH_NAME(Vec3i, ncross)(JNIEnv* env, jobject jthis, jobject jv) {
    auto pt = jni::fromJava<Vec3i>(env, jthis);
    auto ptv = jni::fromJava<Vec3i>(env, jv);

    auto ptr = std::make_shared<Vec3i>(cross(*pt, *ptv));
    ptr->register_jni(true);

    return ptr->get_jhandle();
}
extern "C" JNIEXPORT
long JNICALL METH_NAME(Vec3f, ncross)(JNIEnv* env, jobject jthis, jobject jv) {
    auto pt = jni::fromJava<Vec3f>(env, jthis);
    auto ptv = jni::fromJava<Vec3f>(env, jv);

    auto ptr = std::make_shared<Vec3f>(cross(*pt, *ptv));
    ptr->register_jni(true);

    return ptr->get_jhandle();
}