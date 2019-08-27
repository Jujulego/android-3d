//
// Created by julien on 28/08/2019.
//
#include <GLES3/gl32.h>

#include "shader.h"

using namespace gpu;

// Constructor
Shader::Shader(GLenum const& type): m_type(type) {
    m_shader = glCreateShader(type);
}

// Methods
void Shader::compile() {
    glCompileShader(m_shader);

    m_compiled = true;
}

std::string Shader::getSource() const {
    GLint length = 0;
    std::string source;

    // get length
    glGetShaderiv(m_shader, GL_SHADER_SOURCE_LENGTH, &length);
    source.reserve(length);

    // get source
    glGetShaderSource(m_shader, length, nullptr, source.data());

    return source;
}

void Shader::setSource(std::string const& source) {
    const GLchar const* data = source.data();
    glShaderSource(m_shader, 1, &data, nullptr);

    m_compiled = false;
}

bool Shader::hasError() const {
    GLint success;
    glGetShaderiv(m_shader, GL_COMPILE_STATUS, &success);

    return !success;
}

std::string Shader::getError() const {
    GLint length = 0;
    std::string error;

    // get length
    glGetShaderiv(m_shader, GL_INFO_LOG_LENGTH, &length);
    error.reserve(length);

    // get info log
    glGetShaderInfoLog(m_shader, length, nullptr, error.data());

    return error;
}