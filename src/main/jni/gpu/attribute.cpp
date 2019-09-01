//
// Created by julien on 01/09/2019.
//
#include <memory>
#include <string>

#include <GLES3/gl32.h>

#include "jnitools.h"

#include "attribute.h"
#include "macros.h"
#include "program.h"

using namespace gpu;

// Constructors
Attribute::Attribute(GLint const& index): m_known_index(index) {}
Attribute::Attribute(std::string const& name): m_name(name) {}

// Methods
GLint Attribute::getIndex(GLuint const& program) const {
    // Known index
    if (m_known_index != GL_INVALID_INDEX) {
        return m_index;
    }

    // Search for it
    GLint index = glGetAttribLocation(program, m_name.data());
    if (index == GL_INVALID_INDEX) {
        throw ProgramError("Attribute " + m_name + " not found");
    }

    return index;
}

void Attribute::prepare(GLuint const& program) {
    // Get index
    m_index = getIndex(program);
}

// JNI
extern "C" JNIEXPORT
jlong JNICALL METH_NAME(Attribute, create__I)(JNIEnv*, jclass, jint index) {
    auto pt = std::make_shared<Attribute>(index);
    pt->register_jni(true);

    return pt->get_jhandle();
}

extern "C" JNIEXPORT
jlong JNICALL METH_NAME(Attribute, create__Ljava_lang_String_2)(JNIEnv* env, jclass, jstring jname) {
    auto name = jni::fromJava<std::string>(env, jname);

    auto pt = std::make_shared<Attribute>(name);
    pt->register_jni(true);

    return pt->get_jhandle();
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(Attribute, prepare)(JNIEnv* env, jobject jthis, jobject jprogram) {
    auto pt = jni::fromJava<Attribute>(env, jthis);
    auto program = jni::fromJava<Program>(env, jprogram);

    pt->prepare(program->program());
}