//
// Created by julien on 21/04/2019.
// Inspired by https://github.com/pfultz2/Cloak/wiki/C-Preprocessor-tricks,-tips,-and-idioms#deferred-expression
//
#pragma once

#include "dec.h"
#include "eval.h"
#include "utils.h"
#include "when.h"

/**
 * Computes 'macro' 'count' times
 *
 * Example :
 * #define M(i, _) i
 * REPEAT(8, M, ~) // expand to 0 1 2 3 4 5 6 7 8
 */
#define REPEAT(count, macro, ...) EVAL(_REPEAT(count, macro, __VA_ARGS__));

#define _REPEAT(count, macro, ...)           \
    WHEN(count) (                           \
        OBSTRUCT(REPEAT_INDIRECT)() (       \
            DEC(count), macro, __VA_ARGS__  \
        )                                   \
        OBSTRUCT(macro) (                   \
            DEC(count), __VA_ARGS__         \
        )                                   \
    )

#define REPEAT_INDIRECT() _REPEAT