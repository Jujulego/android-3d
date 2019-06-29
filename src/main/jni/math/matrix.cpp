//
// Created by julien on 22/04/2019.
//
#include <jni.h>

#include "matrix.h"
#include "macros.h"

using namespace math;

// JNI
MAT_JNI(Mat2i, Vec2i, jint)
MAT_JNI(Mat3i, Vec3i, jint)
MAT_JNI(Mat4i, Vec4i, jint)

MAT_JNI(Mat2f, Vec2f, jfloat)
MAT_JNI(Mat3f, Vec3f, jfloat)
MAT_JNI(Mat4f, Vec4f, jfloat)

extern "C" JNIEXPORT
jobject JNICALL METH_NAME(Mat4i, scale)(JNIEnv* env, jobject jthis, jint fx, jint fy, jint fz) {
    auto pt = jni::fromJava<Mat4i>(env, jthis);
    matrix::scale(*pt, fx, fy, fz);

    return jthis;
}
extern "C" JNIEXPORT
jobject JNICALL METH_NAME(Mat4f, scale)(JNIEnv* env, jobject jthis, jfloat fx, jfloat fy, jfloat fz) {
    auto pt = jni::fromJava<Mat4f>(env, jthis);
    matrix::scale(*pt, fx, fy, fz);

    return jthis;
}

extern "C" JNIEXPORT
jobject JNICALL METH_NAME(Mat4i, translate)(JNIEnv* env, jobject jthis, jint dx, jint dy, jint dz) {
    auto pt = jni::fromJava<Mat4i>(env, jthis);
    matrix::translate(*pt, dx, dy, dz);

    return jthis;
}
extern "C" JNIEXPORT
jobject JNICALL METH_NAME(Mat4f, translate)(JNIEnv* env, jobject jthis, jfloat dx, jfloat dy, jfloat dz) {
    auto pt = jni::fromJava<Mat4f>(env, jthis);
    matrix::translate(*pt, dx, dy, dz);

    return jthis;
}

extern "C" JNIEXPORT
long JNICALL METH_NAME(Mat4i, nrotate)(JNIEnv*, jclass, jdouble a, jint x, jint y, jint z) {
    auto pt = std::make_shared<Mat4i>(matrix::rotate(a, x, y, z));
    pt->register_jni(true);

    return pt->get_jhandle();
}
extern "C" JNIEXPORT
long JNICALL METH_NAME(Mat4f, nrotate)(JNIEnv*, jclass, jdouble a, jfloat x, jfloat y, jfloat z) {
    auto pt = std::make_shared<Mat4f>(matrix::rotate(a, x, y, z));
    pt->register_jni(true);

    return pt->get_jhandle();
}