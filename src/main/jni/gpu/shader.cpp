//
// Created by julien on 28/08/2019.
//
#include <memory>

#include <android/log.h>
#include <GLES3/gl32.h>

#include "jnitools.h"
#include "macros.h"
#include "shader.h"

#define LOG_DEBUG(...) __android_log_print(ANDROID_LOG_DEBUG, "gpu::Shader", __VA_ARGS__)

using namespace gpu;

// Constructor
Shader::Shader(GLenum const& type): m_type(type) {}

// Methods
void Shader::compile() {
    // Create shader
    m_shader = glCreateShader(m_type);

    // Add sources
    GLchar const* src = m_source.data();
    glShaderSource(m_shader, 1, &src, nullptr);

    // Compile it
    glCompileShader(m_shader);

    // check for error
    GLint success;
    glGetShaderiv(m_shader, GL_COMPILE_STATUS, &success);

    if (!success) {
        GLint length = 0;
        std::string error;

        // get length
        glGetShaderiv(m_shader, GL_INFO_LOG_LENGTH, &length);
        error.resize(length);

        // get info log
        glGetShaderInfoLog(m_shader, length, nullptr, error.data());

        // throw error
        throw ShaderError(error);
    }
}

void Shader::destroy() {
    if (m_shader != GL_INVALID_INDEX) {
        glDeleteShader(m_shader);
        m_shader = GL_INVALID_INDEX;
    }
}

GLuint const& Shader::shader() const {
    return m_shader;
}

std::string& Shader::source() {
    return m_source;
}

std::string const& Shader::source() const {
    return m_source;
}

// Error
ShaderError::ShaderError(std::string const& error): GPUError(error) {}

std::string ShaderError::javaName() const {
    return "net/capellari/julien/threed/gpu/ShaderError";
}

// JNI
extern "C" JNIEXPORT
jlong JNICALL METH_NAME(Shader, create)(JNIEnv*, jclass, jint type) {
    auto pt = std::make_shared<Shader>(type);
    pt->register_jni(true);

    return pt->get_jhandle();
}

extern "C" JNIEXPORT
jstring JNICALL METH_NAME(Shader, getSource)(JNIEnv* env, jobject jthis) {
    auto pt = jni::fromJava<Shader>(env, jthis);

    return jni::toJava(env, pt->source());
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(Shader, setSource)(JNIEnv* env, jobject jthis, jstring jsrc) {
    auto pt = jni::fromJava<Shader>(env, jthis);
    auto src = jni::fromJava<std::string>(env, jsrc);

    pt->source() = src;
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(Shader, compile)(JNIEnv* env, jobject jthis) {
    auto pt = jni::fromJava<Shader>(env, jthis);

    try {
        pt->compile();
    } catch (GPUError& err) {
        err.javaThrow(env);
    }
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(Shader, destroy)(JNIEnv* env, jobject jthis) {
    auto pt = jni::fromJava<Shader>(env, jthis);

    pt->destroy();
}