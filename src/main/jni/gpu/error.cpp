//
// Created by julien on 01/09/2019.
//
#include "jnitools.h"

#include "error.h"

using namespace gpu;

// Constructor
GPUError::GPUError(std::string const& error): m_error(error) {}

// Methodes
std::string GPUError::javaName() const {
    return "net/capellari/julien/threed/gpu/GPUError";
}

void GPUError::javaThrow(JNIEnv* env) const {
    jni::javaThrow(env, javaName(), m_error);
}

const char* GPUError::what() const noexcept {
    return m_error.data();
}