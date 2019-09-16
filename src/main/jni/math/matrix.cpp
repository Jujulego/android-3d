//
// Created by julien on 22/04/2019.
//
#include <jni.h>

#include "matrix.h"
#include "macros.h"

using namespace math;

// OpenGL call
#define GLUM_NAME(L, C) CAT(CAT(glUniformMatrix, IF(EQ(L, C))(CAT(CAT(C, x), L), L)), fv)
#define GLUM_TPL(L, C)                                      \
    template<> void glUniformMatrix<float,L,C>(GLint location, GLsizei count, GLboolean transpose, GLfloat const* value) { \
        GLUM_NAME(L, C)(location, count, transpose, value); \
    }

GLUM_TPL(2, 2)
GLUM_TPL(3, 2)
GLUM_TPL(4, 2)
GLUM_TPL(2, 3)
GLUM_TPL(3, 3)
GLUM_TPL(4, 3)
GLUM_TPL(2, 4)
GLUM_TPL(3, 4)
GLUM_TPL(4, 4)

// Utils
Mat4f matrix::frustum(float left, float right, float bottom, float top, float near, float far) {
    // Compute ratios
    float rw = 1.0f / (right - left);
    float rh = 1.0f / (top - bottom);
    float rd = 1.0f / (near - far);

    // Construct matrix
    Mat4f m;
    m[P(0,0)] = 2.0f * (near * rw);
    m[P(1,1)] = 2.0f * (near * rh);

    m[P(0,2)] = (right + left) * rw;
    m[P(1,2)] = (top + bottom) * rh;
    m[P(2,2)] = (far + near) * rd;
    m[P(2,3)] = 2.0f * (far * near * rd);

    m[P(3,2)] = -1.0f;

    return m;
}

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

extern "C" JNIEXPORT
long JNICALL METH_NAME(Mat4f, nfrustum)(JNIEnv* env, jclass, jfloat left, jfloat right, jfloat bottom, jfloat top, jfloat near, jfloat far) {
    auto pt = std::make_shared<Mat4f>(matrix::frustum(left, right, bottom, top, near, far));
    pt->register_jni(true);

    return pt->get_jhandle();
}