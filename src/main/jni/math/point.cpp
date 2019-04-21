//
// Created by julien on 20/04/2019.
//
#include "point.h"

using namespace math;

// JNI
POINT_JNI(Point2i, jint, x, y)
POINT_JNI(Point3i, jint, x, y, z)
POINT_JNI(Point4i, jint, x, y, z, a)

POINT_JNI(Point2f, jfloat, x, y)
POINT_JNI(Point3f, jfloat, x, y, z)
POINT_JNI(Point4f, jfloat, x, y, z, a)