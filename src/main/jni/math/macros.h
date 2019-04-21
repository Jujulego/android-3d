//
// Created by julien on 21/04/2019.
//
#pragma once

#include "../macros.h"
#include "../macros/cat.h"

// Macros
#define METH_NAME(cls, name) Java_net_capellari_julien_threed_##cls##_##name

#define ARG(n, f) WHEN(NOT(f))(,) n
#define ARG_TYPE(t) PRIMITIVE_CAT(ARG_TYPE_, t)
#define ARG_TYPE_jint(n, f) WHEN(NOT(f))(,) jint n
#define ARG_TYPE_jfloat(n, f) WHEN(NOT(f))(,) jfloat n

#define ARGS(x, ...) FOR_EACH(ARG, x, __VA_ARGS__)
#define TYPE_ARGS(type, x, ...) ARGS(FOR_EACH(ARG_TYPE(type), x, __VA_ARGS__))