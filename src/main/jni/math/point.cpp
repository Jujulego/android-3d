//
// Created by julien on 20/04/2019.
//
#include "point.h"

#include <memory>

#include "jnitools.h"

using namespace math;

// JNI
extern "C" JNIEXPORT
jlong JNICALL Java_net_capellari_julien_threed_Point2i_create(JNIEnv*, jclass, jint x, jint y) {
    auto pt = std::make_shared<Point2i>(x, y);
    pt->register_jni(true);

    return pt->get_jhandle();
}

extern "C" JNIEXPORT
jint JNICALL Java_net_capellari_julien_threed_Point2i_getCoord(JNIEnv* env, jobject jthis, jint i) {
    auto pt = jni::fromJava<Point2i>(env, jthis);
    return (*pt)[i];
}

extern "C" JNIEXPORT
void JNICALL Java_net_capellari_julien_threed_Point2i_setCoord(JNIEnv* env, jobject jthis, jint i, jint v) {
    auto pt = jni::fromJava<Point2i>(env, jthis);
    (*pt)[i] = v;
}

extern "C" JNIEXPORT
jboolean JNICALL Java_net_capellari_julien_threed_Point2i_equal(JNIEnv* env, jobject jthis, jobject other) {
    auto ptt = jni::fromJava<Point2i>(env, jthis);
    auto pto = jni::fromJava<Point2i>(env, jthis);

    return (jboolean) ((*ptt) == (*pto));
}

extern "C" JNIEXPORT
jlong JNICALL Java_net_capellari_julien_threed_Point2i_plus(JNIEnv* env, jobject jthis) {
    auto opt = jni::fromJava<Point2i>(env, jthis);
    auto pt = std::make_shared<Point2i>(+(*opt));
    pt->register_jni(true);

    return pt->get_jhandle();
}

extern "C" JNIEXPORT
jlong JNICALL Java_net_capellari_julien_threed_Point2i_minus(JNIEnv* env, jobject jthis) {
    auto opt = jni::fromJava<Point2i>(env, jthis);
    auto pt = std::make_shared<Point2i>(-(*opt));
    pt->register_jni(true);

    return pt->get_jhandle();
}