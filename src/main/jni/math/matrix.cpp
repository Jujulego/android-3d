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
jobject JNICALL METH_NAME(Mat4i, scale)(JNIEnv* env, jobject jthis, jobject jf) {
    auto pt = jni::fromJava<Mat4i>(env, jthis);
    auto ptf = jni::fromJava<Vec3i>(env, jf);
    matrix::scale(*pt, *ptf);

    return jthis;
}
extern "C" JNIEXPORT
jobject JNICALL METH_NAME(Mat4f, scale)(JNIEnv* env, jobject jthis, jobject jf) {
    auto pt = jni::fromJava<Mat4f>(env, jthis);
    auto ptf = jni::fromJava<Vec3f>(env, jf);
    matrix::scale(*pt, *ptf);

    return jthis;
}

extern "C" JNIEXPORT
jobject JNICALL METH_NAME(Mat4i, translate)(JNIEnv* env, jobject jthis, jobject jd) {
    auto pt = jni::fromJava<Mat4i>(env, jthis);
    auto ptd = jni::fromJava<Vec3i>(env, jd);
    matrix::translate(*pt, *ptd);

    return jthis;
}
extern "C" JNIEXPORT
jobject JNICALL METH_NAME(Mat4f, translate)(JNIEnv* env, jobject jthis, jobject jd) {
    auto pt = jni::fromJava<Mat4f>(env, jthis);
    auto ptd = jni::fromJava<Vec3f>(env, jd);
    matrix::translate(*pt, *ptd);

    return jthis;
}

extern "C" JNIEXPORT
long JNICALL METH_NAME(Mat4i, nrotate)(JNIEnv* env, jclass, jdouble a, jobject jaxe) {
    auto ptaxe = jni::fromJava<Vec3i>(env, jaxe);

    auto pt = std::make_shared<Mat4i>(matrix::rotate(a, *ptaxe));
    pt->register_jni(true);

    return pt->get_jhandle();
}
extern "C" JNIEXPORT
long JNICALL METH_NAME(Mat4f, nrotate)(JNIEnv* env, jclass, jdouble a, jobject jaxe) {
    auto ptaxe = jni::fromJava<Vec3f>(env, jaxe);

    auto pt = std::make_shared<Mat4f>(matrix::rotate(a, *ptaxe));
    pt->register_jni(true);

    return pt->get_jhandle();
}

extern "C" JNIEXPORT
long JNICALL METH_NAME(Mat4i, nlookAt)(JNIEnv* env, jclass, jobject jeye, jobject jcenter, jobject jup) {
    auto pteye = jni::fromJava<Vec3i>(env, jeye);
    auto ptcenter = jni::fromJava<Vec3i>(env, jcenter);
    auto ptup = jni::fromJava<Vec3i>(env, jup);

    auto pt = std::make_shared<Mat4i>(matrix::lookAt(*pteye, *ptcenter, *ptup));
    pt->register_jni(true);

    return pt->get_jhandle();
}
extern "C" JNIEXPORT
long JNICALL METH_NAME(Mat4f, nlookAt)(JNIEnv* env, jclass, jobject jeye, jobject jcenter, jobject jup) {
    auto pteye = jni::fromJava<Vec3f>(env, jeye);
    auto ptcenter = jni::fromJava<Vec3f>(env, jcenter);
    auto ptup = jni::fromJava<Vec3f>(env, jup);

    auto pt = std::make_shared<Mat4f>(matrix::lookAt(*pteye, *ptcenter, *ptup));
    pt->register_jni(true);

    return pt->get_jhandle();
}