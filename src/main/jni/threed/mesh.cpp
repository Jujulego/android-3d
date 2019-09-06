//
// Created by julien on 05/09/2019.
//
#include <memory>

#include <android/log.h>
#include <GLES3/gl32.h>

#include "macros.h"
#include "mesh.h"

#define LOG_DEBUG(...) __android_log_print(ANDROID_LOG_DEBUG, "Mesh", __VA_ARGS__)

// Constructor
Mesh::Mesh(JNIEnv* env): m_indices(env, 0) {
    m_vertices = std::make_shared<math::VectorArray3f>();
    m_vertices->register_jni();
}

// Methods
void Mesh::init(gpu::VertexAttribute& vattr) {
    // Initialise VAO
    m_vao = std::make_shared<gpu::VertexArray>();
    m_vao->bind();

    // VBO
    m_vbo = std::make_shared<gpu::Buffer>();
    m_vbo->bind(GL_ARRAY_BUFFER);

    if (m_vertices->size() > 0) {
        m_vbo->setDataArray(*m_vertices, GL_STATIC_DRAW);
    }

    vattr.enable(3 * sizeof(float));

    // EBO
    m_ebo = std::make_shared<gpu::Buffer>();
    m_ebo->bind(GL_ELEMENT_ARRAY_BUFFER);

    if (m_indices.size() > 0) {
        m_ebo->setData(m_indices.data(), m_indices.size() * sizeof(int), GL_STATIC_DRAW);
    }
}

void Mesh::draw() {
    m_vao->bind();

    if (m_indices.size() > 0) {
        glDrawElements(GL_TRIANGLES, m_indices.size(), GL_UNSIGNED_INT, 0);
    } else if (m_vertices->size() > 0) {
        glDrawArrays(GL_TRIANGLES, 0, m_vertices->size());
    }
}

jni::array<jintArray> const& Mesh::indices() const {
    return m_indices;
}

void Mesh::setIndices(jni::array<jintArray> const& indices) {
    m_indices = indices;

    // Sync buffer
    if (m_ebo != nullptr) {
        m_ebo->bind(GL_ELEMENT_ARRAY_BUFFER);
        m_ebo->setData(indices.data(), indices.size() * sizeof(int), GL_STATIC_DRAW);
    }
}

std::shared_ptr<math::VectorArray3f> const& Mesh::vertices() const {
    return m_vertices;
}

void Mesh::setVertices(std::shared_ptr<math::VectorArray3f> const& vertices) {
    m_vertices = vertices;

    // Sync buffer
    if (m_vbo != nullptr) {
        m_vbo->bind(GL_ARRAY_BUFFER);
        m_vbo->setDataArray(*m_vertices, GL_STATIC_DRAW);
    }
}

// JNI
extern "C" JNIEXPORT
jlong JNICALL METH_NAME(Mesh, create)(JNIEnv* env, jclass) {
    auto pt = std::make_shared<Mesh>(env);
    pt->register_jni(true);

    return pt->get_jhandle();
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(Mesh, init)(JNIEnv* env, jobject jthis, jobject jvattr) {
    auto pt = jni::fromJava<Mesh>(env, jthis);
    auto vattr = jni::fromJava<gpu::VertexAttribute>(env, jvattr);

    pt->init(*vattr);
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(Mesh, draw)(JNIEnv* env, jobject jthis) {
    auto pt = jni::fromJava<Mesh>(env, jthis);

    pt->draw();
}

extern "C" JNIEXPORT
jintArray JNICALL METH_NAME(Mesh, getIndices)(JNIEnv* env, jobject jthis) {
    auto pt = jni::fromJava<Mesh>(env, jthis);

    return pt->indices().jref();
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(Mesh, setIndices)(JNIEnv* env, jobject jthis, jintArray jdata) {
    auto pt = jni::fromJava<Mesh>(env, jthis);
    auto data = jni::array<jintArray>(env, jdata);

    pt->setIndices(data);
}

extern "C" JNIEXPORT
jlong JNICALL METH_NAME(Mesh, ngetVertices)(JNIEnv* env, jobject jthis) {
    auto pt = jni::fromJava<Mesh>(env, jthis);

    auto vertices = pt->vertices();
    vertices->acquire();

    return vertices->get_jhandle();
}

extern "C" JNIEXPORT
void JNICALL METH_NAME(Mesh, nsetVertices)(JNIEnv* env, jobject jthis, jobject jvertices) {
    auto pt = jni::fromJava<Mesh>(env, jthis);
    auto vertices = jni::fromJava<math::VectorArray3f>(env, jvertices);

    pt->setVertices(vertices);
}