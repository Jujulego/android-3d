//
// Created by julien on 21/04/2019.
// Inspired by https://github.com/pfultz2/Cloak/wiki/C-Preprocessor-tricks,-tips,-and-idioms#deferred-expression
//
#pragma once

#include "compl.h"
#include "not.h"

/**
 * Convert condition to boolean
 */
#define BOOL(c) COMPL(NOT(c))