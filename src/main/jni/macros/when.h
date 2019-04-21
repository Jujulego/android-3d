//
// Created by julien on 21/04/2019.
// Inspired by https://github.com/pfultz2/Cloak/wiki/C-Preprocessor-tricks,-tips,-and-idioms#deferred-expression
//
#pragma once

#include "if.h"
#include "utils.h"

/**
 * Example :
 * WHEN(test) (if true ...)
 */
#define WHEN(c) IF(c)(EXPAND, EAT)