//
// Created by julien on 05/09/2019.
//
#pragma once

#include <memory>

#include "gpu.h"
#include "jnitools.h"
#include "math/array.h"

// Class
class Mesh: public jni::JNIClass {
private:
    // Attributes
    jni::array<jintArray> m_indices;
    std::shared_ptr<math::VectorArray3f> m_vertices = nullptr;

    std::shared_ptr<gpu::VertexArray> m_vao = nullptr;
    std::shared_ptr<gpu::Buffer> m_ebo = nullptr;
    std::shared_ptr<gpu::Buffer> m_vbo = nullptr;

public:
    // Constructor
    Mesh(JNIEnv* env);

    // Methods
    void init(gpu::VertexAttribute& vattr);
    void draw();

    jni::array<jintArray> const& indices() const;
    void setIndices(jni::array<jintArray> const& indices);

    std::shared_ptr<math::VectorArray3f> const& vertices() const;
    void setVertices(std::shared_ptr<math::VectorArray3f> const& vertices);
};