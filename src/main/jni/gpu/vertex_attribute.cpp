//
// Created by julien on 01/09/2019.
//
#include <memory>
#include <string>

#include <GLES3/gl32.h>

#include "jnitools.h"

#include "vertex_attribute.h"
#include "macros.h"
#include "program.h"

using namespace gpu;

// Constructors
VertexAttribute::VertexAttribute(GLuint const& location, GLenum const& type, GLint const& size)
        : m_location(location), m_type(type), m_size(size) {}

VertexAttribute::VertexAttribute(std::string const& name, GLenum const& type, GLint const& size)
        : m_name(name), m_type(type), m_size(size) {}

// Methods
GLuint VertexAttribute::getIndex(GLuint const& program) const {
    // Known index
    if (m_location != GL_INVALID_INDEX) {
        return m_location;
    }

    // Search for it
    GLuint index = glGetAttribLocation(program, m_name.data());
    if (index == GL_INVALID_INDEX) {
        throw ProgramError("Attribute " + m_name + " not found");
    }

    return index;
}

void VertexAttribute::prepare(GLuint const& program) {
    // Get index
    m_index = getIndex(program);
}

void VertexAttribute::enable(GLsizei const& stride, GLsizeiptr const& offset, bool normalized) {
    // Enable attribute !
    glVertexAttribPointer(m_index, m_size, m_type, normalized ? GL_TRUE : GL_FALSE, stride, (void*) offset);
    glEnableVertexAttribArray(m_index);
}

// JNI
extern "C" JNIEXPORT
jlong JNICALL METH_NAME(VertexAttribute, create__III)(JNIEnv*, jclass, jint location, jint type, jint size) {
    auto pt = std::make_shared<VertexAttribute>(location, type, size);
    pt->register_jni(true);

    return pt->get_jhandle();
}

extern "C" JNIEXPORT
jlong JNICALL METH_NAME(VertexAttribute, create__Ljava_lang_String_2II)(JNIEnv* env, jclass, jstring jname, jint type, jint size) {
    auto name = jni::fromJava<std::string>(env, jname);

    auto pt = std::make_shared<VertexAttribute>(name, type, size);
    pt->register_jni(true);

    return pt->get_jhandle();
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(VertexAttribute, prepare)(JNIEnv* env, jobject jthis, jobject jprogram) {
    auto pt = jni::fromJava<VertexAttribute>(env, jthis);
    auto program = jni::fromJava<Program>(env, jprogram);

    pt->prepare(program->program());
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(VertexAttribute, enable)(JNIEnv* env, jobject jthis, jint stride, jint offset, jboolean normalized) {
    auto pt = jni::fromJava<VertexAttribute>(env, jthis);

    pt->enable(stride, offset, normalized);
}