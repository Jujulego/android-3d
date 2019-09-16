//
// Created by julien on 21/04/2019.
// Inspired by https://github.com/pfultz2/Cloak/wiki/C-Preprocessor-tricks,-tips,-and-idioms#deferred-expression
//
#pragma once

#include "cat.h"

/**
 * Binary and operator
 */
#define AND(x, y) _AND(x)(y)

#define _AND(x) PRIMITIVE_CAT(_AND_, x)
#define _AND_0(y) 0
#define _AND_1(y) y

/**
 * Binary or operator
 */
#define OR(x, y) _OR(x)(y)

#define _OR(x) PRIMITIVE_CAT(_OR_, x)
#define _OR_0(y) y
#define _OR_1(y) 1