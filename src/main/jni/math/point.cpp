//
// Created by julien on 20/04/2019.
//
#include "point.h"
#include "vector.h"

using namespace math;

// JNI
POINT_JNI(Point2i, Vec2i, jint, x, y)
POINT_JNI(Point3i, Vec3i, jint, x, y, z)
POINT_JNI(Point4i, Vec4i, jint, x, y, z, a)

POINT_JNI(Point2f, Vec2f, jfloat, x, y)
POINT_JNI(Point3f, Vec3f, jfloat, x, y, z)
POINT_JNI(Point4f, Vec4f, jfloat, x, y, z, a)
