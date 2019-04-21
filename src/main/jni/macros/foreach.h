//
// Created by julien on 21/04/2019.
//
#pragma once

#include "cat.h"
#include "check.h"
#include "eval.h"
#include "not.h"
#include "utils.h"
#include "when.h"

/**
 * Test if is after last item
 */
#define IS_AFTER_LAST(x, ...) CHECK(PRIMITIVE_CAT(IS_AFTER_LAST_, x))
#define IS_AFTER_LAST_ PROBE(~)

/**
 * Applies macro to each parameters
 */
#define FOR_EACH(macro, x, ...) EVAL(_FOR_EACH(macro, 1, x, __VA_ARGS__))

#define _FOR_EACH(macro, first, x, ...)                         \
    OBSTRUCT(macro)(x, first)                                   \
    WHEN(NOT(IS_AFTER_LAST(__VA_ARGS__))) (                     \
        OBSTRUCT(FOR_EACH_INDIRECT)() (macro, 0, __VA_ARGS__)   \
    )

#define FOR_EACH_INDIRECT() _FOR_EACH