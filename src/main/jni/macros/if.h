//
// Created by julien on 21/04/2019.
// Inspired by https://github.com/pfultz2/Cloak/wiki/C-Preprocessor-tricks,-tips,-and-idioms#deferred-expression
//
#pragma once

#include "bool.h"
#include "cat.h"

/**
 * Condition if
 *
 * Use:
 *   IF(test)(if_true, if_false)
 */
#define IF(c) IIF(BOOL(c))

#define IIF(c) PRIMITIVE_CAT(IIF_, c)
#define IIF_0(t, ...) __VA_ARGS__
#define IIF_1(t, ...) t