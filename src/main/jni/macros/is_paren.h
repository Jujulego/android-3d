//
// Created by julien on 21/04/2019.
// Inspired by https://github.com/pfultz2/Cloak/wiki/C-Preprocessor-tricks,-tips,-and-idioms#deferred-expression
//
#pragma once

#include "check.h"

/**
 * Test if x is parenthesis
 */
#define IS_PAREN(x) CHECK(IS_PAREN_PROBE x)
#define IS_PAREN_PROBE(...) PROBE(~)