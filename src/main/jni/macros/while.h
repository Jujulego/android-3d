//
// Created by julien on 21/04/2019.
// Inspired by https://github.com/pfultz2/Cloak/wiki/C-Preprocessor-tricks,-tips,-and-idioms#deferred-expression
//
#pragma once

#include "eval.h"
#include "if.h"
#include "utils.h"

/**
 * Computes 'op' while 'pred' is true
 */
#define WHILE(pred, op, ...) EVAL(_WHILE(pred, op, __VA_ARGS__))

#define _WHILE(pred, op, ...)            \
    IF(pred(__VA_ARGS__)) (             \
        OBSTRUCT(WHILE_INDIRECT)() (    \
            pred, op, op(__VA_ARGS__)   \
        ),                              \
        __VA_ARGS__                     \
    )

#define WHILE_INDIRECT() _WHILE