//
// Created by julien on 20/04/2019.
//
#include "vector.h"

using namespace math;

// JNI
VECTOR_JNI(Vec2i, jint, x, y)
VECTOR_JNI(Vec3i, jint, x, y, z)
VECTOR_JNI(Vec4i, jint, x, y, z, a)

VECTOR_JNI(Vec2f, jfloat, x, y)
VECTOR_JNI(Vec3f, jfloat, x, y, z)
VECTOR_JNI(Vec4f, jfloat, x, y, z, a)