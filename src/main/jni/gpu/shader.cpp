//
// Created by julien on 28/08/2019.
//
#include <memory>

#include <android/log.h>
#include <GLES3/gl32.h>

#include "macros.h"
#include "shader.h"

using namespace gpu;

// Constructor
Shader::Shader(GLenum const& type): m_type(type) {
    m_shader = glCreateShader(type);
}

// Methods
void Shader::compile() {
    // compile
    glCompileShader(m_shader);

    // check for error
    GLint success;
    glGetShaderiv(m_shader, GL_COMPILE_STATUS, &success);

    if (!success) {
        GLint length = 0;
        std::string error;

        // get length
        glGetShaderiv(m_shader, GL_INFO_LOG_LENGTH, &length);
        error.reserve(length);

        // get info log
        glGetShaderInfoLog(m_shader, length, nullptr, error.data());

        // throw error
        throw ShaderError(error);
    }
}

GLuint const& Shader::shader() const {
    return m_shader;
}

void Shader::setSource(std::string const& source) {
    const GLchar const* data = source.data();
    glShaderSource(m_shader, 1, &data, nullptr);
}

// Error
ShaderError::ShaderError(std::string const& error): m_error(error) {}

std::string const& ShaderError::error() const {
    return m_error;
}

const char* ShaderError::what() const {
    return m_error.data();
}

// JNI
extern "C" JNIEXPORT
jlong JNICALL METH_NAME(Shader, create)(JNIEnv*, jclass, jint type) {
    auto pt = std::make_shared<Shader>(type);
    pt->register_jni(true);

    return pt->get_jhandle();
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(Shader, setSource)(JNIEnv* env, jobject jthis, jstring jsrc) {
    auto pt = jni::fromJava<Shader>(env, jthis);
    auto src = jni::fromJava<std::string>(env, jsrc);

    pt->setSource(src);
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(Shader, compile)(JNIEnv* env, jobject jthis) {
    auto pt = jni::fromJava<Shader>(env, jthis);

    try {
        pt->compile();
    } catch (ShaderError& err) {
        jni::javaThrow(env, "net/capellari/julien/threed/gpu/ShaderError", err.error());
    }
}