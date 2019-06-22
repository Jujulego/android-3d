//
// Created by julien on 22/04/2019.
//
#include <jni.h>

#include "matrix.h"
#include "macros.h"

using namespace math;

MAT_JNI(Mat2i, Point2i, Vec2i, jint)
MAT_JNI(Mat3i, Point3i, Vec3i, jint)
MAT_JNI(Mat4i, Point4i, Vec4i, jint)

MAT_JNI(Mat2f, Point2f, Vec2f, jfloat)
MAT_JNI(Mat3f, Point3f, Vec3f, jfloat)
MAT_JNI(Mat4f, Point4f, Vec4f, jfloat)
