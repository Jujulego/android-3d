//
// Created by julien on 21/04/2019.
// Inspired by https://github.com/pfultz2/Cloak/wiki/C-Preprocessor-tricks,-tips,-and-idioms#deferred-expression
//
#pragma once

/**
 * Base for conditions :
 * - CHECK(xxx)      => 0
 * - CHECK(PROBE(~)) => 1
 */
#define CHECK(...) CHECK_N(__VA_ARGS__, 0,)
#define CHECK_N(x, n, ...) n
#define PROBE(x) x, 1