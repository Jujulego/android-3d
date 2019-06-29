//
// Created by julien on 20/04/2019.
//
#include <cmath>

#include "outils.h"

// Outils
double math::length(double x, double y) {
    return sqrt(x * x + y * y);
}

double math::length(double x, double y, double z) {
    return sqrt(x * x + y * y + z * z);
}