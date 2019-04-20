//
// Created by julien on 20/04/2019.
//
#include "point.h"

#include <memory>

#include "jnitools.h"

using namespace math;

// JNI
extern "C" JNIEXPORT
jlong JNICALL Java_net_capellari_julien_threed_jni_Points_createPoint_1i2(JNIEnv*, jclass, jint x, jint y) {
    auto pt = std::make_shared<Point2i>(x, y);
    pt->register_jni(true);

    return pt->get_jhandle();
}

extern "C" JNIEXPORT
jint JNICALL Java_net_capellari_julien_threed_jni_Points_getPointCoord_1i2(JNIEnv*, jclass, jlong handle, jint i) {
    auto pt = jni::fromHandle<Point2i>(handle);
    return (*pt)[i];
}