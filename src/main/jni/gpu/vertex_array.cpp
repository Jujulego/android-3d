//
// Created by julien on 04/09/2019.
//
#include <memory>

#include <GLES3/gl32.h>

#include "jnitools.h"

#include "macros.h"
#include "vertex_array.h"

using namespace gpu;

// Constructor
VertexArray::VertexArray() {
    glGenVertexArrays(1, &m_index);
}

// Methods
void VertexArray::bind() {
    glBindVertexArray(m_index);
}

// JNI
extern "C" JNIEXPORT
jlong JNICALL METH_NAME(VertexArray, create)(JNIEnv*, jclass) {
    auto pt = std::make_shared<VertexArray>();
    pt->register_jni(true);

    return pt->get_jhandle();
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(VertexArray, bind)(JNIEnv* env, jobject jthis) {
    auto pt = jni::fromJava<VertexArray>(env, jthis);

    pt->bind();
}