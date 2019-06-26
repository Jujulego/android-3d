//
// Created by julien on 20/04/2019.
//
#include "point.h"
#include "vector.h"

using namespace math;

// JNI
VECTOR_JNI(Vec2i, Point2i, jint, x, y)
VECTOR_JNI(Vec3i, Point3i, jint, x, y, z)
VECTOR_JNI(Vec4i, Point4i, jint, x, y, z, a)

VECTOR_JNI(Vec2f, Point2f, jfloat, x, y)
VECTOR_JNI(Vec3f, Point3f, jfloat, x, y, z)
VECTOR_JNI(Vec4f, Point4f, jfloat, x, y, z, a)