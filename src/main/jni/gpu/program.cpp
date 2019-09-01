//
// Created by julien on 28/08/2019.
//
#include <memory>

#include <android/log.h>
#include <GLES3/gl3.h>

#include "jnitools.h"
#include "macros.h"
#include "program.h"

#define LOG_DEBUG(...) __android_log_print(ANDROID_LOG_DEBUG, "gpu::Program", __VA_ARGS__)

using namespace gpu;

// Methods
void Program::addShader(std::shared_ptr<Shader> const& shader) {
    m_shaders.push_back(shader);
}

void Program::compile() {
    // Create new program
    destroy(); // cleanup previous one
    m_program = glCreateProgram();

    // Compile and attach shaders
    auto it = m_shaders.begin();
    while (it != m_shaders.end()) {
        auto shader = *it;
        shader->compile();

        glAttachShader(m_program, shader->shader());

        ++it;
    }

    // Link program
    glLinkProgram(m_program);

    // check for error
    GLint success;
    glGetProgramiv(m_program, GL_LINK_STATUS, &success);

    if (!success) {
        GLint length = 0;
        std::string error;

        // get length
        glGetProgramiv(m_program, GL_INFO_LOG_LENGTH, &length);
        error.reserve(length);

        // get info log
        glGetProgramInfoLog(m_program, length, nullptr, error.data());

        // throw error
        throw ProgramError(error);
    }

    // Destroy shaders
    while (it != m_shaders.end()) {
        (*it)->destroy();
        ++it;
    }
}

void Program::use() {
    if (m_program != GL_INVALID_INDEX) {
        glUseProgram(m_program);
    }
}

void Program::destroy() {
    if (m_program != GL_INVALID_INDEX) {
        glDeleteProgram(m_program);
        m_program = GL_INVALID_INDEX;
    }
}

// Error
ProgramError::ProgramError(std::string const& error): m_error(error) {}

std::string const& ProgramError::error() const {
    return m_error;
}

const char* ProgramError::what() const noexcept {
    return m_error.data();
}

// JNI
extern "C" JNIEXPORT
jlong JNICALL METH_NAME(Program, create)(JNIEnv*, jclass) {
    auto pt = std::make_shared<Program>();
    pt->register_jni(true);

    return pt->get_jhandle();
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(Program, addShader)(JNIEnv* env, jobject jthis, jobject jshader) {
    auto pt = jni::fromJava<Program>(env, jthis);
    auto shader = jni::fromJava<Shader>(env, jshader);

    pt->addShader(shader);
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(Program, compile)(JNIEnv* env, jobject jthis) {
    auto pt = jni::fromJava<Program>(env, jthis);

    try {
        pt->compile();
    } catch (ShaderError& err) {
        jni::javaThrow(env, "net/capellari/julien/threed/gpu/ShaderError", err.error());
    } catch (ProgramError& err) {
        jni::javaThrow(env, "net/capellari/julien/threed/gpu/ProgramError", err.error());
    }
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(Program, use)(JNIEnv* env, jobject jthis) {
    auto pt = jni::fromJava<Program>(env, jthis);
    pt->use();
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(Program, destroy)(JNIEnv* env, jobject jthis) {
    auto pt = jni::fromJava<Program>(env, jthis);
    pt->destroy();
}