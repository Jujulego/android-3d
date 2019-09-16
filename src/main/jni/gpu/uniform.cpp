//
// Created by julien on 09/09/2019.
//
#include <memory>

#include <GLES3/gl3.h>

#include "jnitools.h"
#include "math/matrix.h"
#undef METH_NAME

#include "macros.h"
#include "program.h"
#include "uniform.h"

using namespace gpu;

// Constructor
Uniform::Uniform(std::string const& name, Program const* program)
        : m_name(name), m_program(program) {}

// Methods
void Uniform::prepare() {
    // Get uniform's index
    m_index = glGetUniformLocation(m_program->program(), m_name.data());
    if (m_index == GL_INVALID_INDEX) {
        throw ProgramError("Uniform " + m_name + " not found");
    }
}

void Uniform::setValue(Uniformable const& value) {
    // Check as index
    if (m_index == GL_INVALID_INDEX) return;

    // Set value
    value.toUniform(m_index);
}

// JNI
extern "C" JNIEXPORT
jlong JNICALL METH_NAME(Uniform, create)(JNIEnv* env, jclass, jstring jname, jobject jprogram) {
    auto name = jni::fromJava<std::string>(env, jname);
    auto program = jni::fromJava<Program>(env, jprogram);

    auto pt = std::make_shared<Uniform>(name, program.get());
    pt->register_jni(true);

    return pt->get_jhandle();
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(Uniform, setNValue)(JNIEnv* env, jobject jthis, jobject jdata) {
    auto pt = jni::fromJava<Uniform>(env, jthis);
    auto data = jni::fromJava<Uniformable>(env, jdata);

    pt->setValue(*data);
}