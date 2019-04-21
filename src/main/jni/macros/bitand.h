//
// Created by julien on 21/04/2019.
// Inspired by https://github.com/pfultz2/Cloak/wiki/C-Preprocessor-tricks,-tips,-and-idioms#deferred-expression
//
#pragma once

#include "cat.h"

/**
 * Binary and operator
 */
#define BITAND(x, y) _BITAND(x)(y)

#define _BITAND(x) PRIMITIVE_CAT(_BITAND_, x)
#define _BITAND_0(y) 0
#define _BITAND_1(y) y