//
// Created by julien on 09/09/2019.
//
#pragma once

#include "cat.h"

/**
 * Equality test: EQ(X, Y) means X == Y
 */
#define EQ(X, Y) CAT(CAT(EQ_, X), CAT(_, Y))
#define EQ_0_0 0
#define EQ_1_1 0
#define EQ_2_2 0
#define EQ_3_3 0
#define EQ_4_4 0
#define EQ_5_5 0
#define EQ_6_6 0
#define EQ_7_7 0
#define EQ_8_8 0
#define EQ_9_9 0