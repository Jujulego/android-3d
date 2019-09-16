//
// Created by julien on 09/09/2019.
//
#pragma once

#include "bit.h"
#include "cat.h"
#include "is_paren.h"
#include "utils.h"

/**
 * Creates a tuple
 */
#define TUPLE(...) (TUPLE, __VA_ARGS__)

/**
 * Remove TUPLE tag
 */
#define TO_PAREN(tuple) _TO_PAREN tuple
#define _TO_PAREN(t, ...) (__VA_ARGS__)

/**
 * Test if is a tuple
 */
#define IS_TUPLE(arg) AND(IS_PAREN(arg), CHECK(_IS_TUPLE arg))
#define _IS_TUPLE(t, ...) PRIMITIVE_CAT(_IS_TUPLE_, t)()
#define _IS_TUPLE_TUPLE(...) PROBE(~)

/**
 * Gets i indexed value
 */
#define GET(tuple, i) PRIMITIVE_CAT(_GET_, i)tuple
#define _GET_0(t, x, ...) x
#define _GET_1(t, a, x, ...) x
#define _GET_2(t, a, b, x, ...) x
#define _GET_3(t, a, b, c, x, ...) x
#define _GET_4(t, a, b, c, d, x, ...) x
#define _GET_5(t, a, b, c, d, e, x, ...) x
#define _GET_6(t, a, b, c, d, e, f, x, ...) x
#define _GET_7(t, a, b, c, d, e, f, g, x, ...) x
#define _GET_8(t, a, b, c, d, e, f, g, h, x, ...) x
#define _GET_9(t, a, b, c, d, e, f, g, h, i, x, ...) x

/**
 * Adds given element to a tuple
 */
#define APPEND(tuple, ...) (_APPEND tuple, __VA_ARGS__)
#define _APPEND(...) __VA_ARGS__

/**
 * Call given macro with a tuple contents as args
 * Needs to be evaluated
 *
 * Usage : EVAL(CALL(TEST)(TUPLE(4, 4))) is same as TEST(4, 4)
 *
 * It can be used in any evaluated macros (as FOR_EACH, REPEAT or WHILE)
 */
#define CALL(macro) DEFER(macro) _CALL
#define _CALL(t, ...) TO_PAREN(t)
