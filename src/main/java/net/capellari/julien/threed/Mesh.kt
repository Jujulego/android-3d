package net.capellari.julien.threed

import net.capellari.julien.threed.gpu.VertexAttribute
import net.capellari.julien.threed.jni.JNIClass

open class Mesh: JNIClass(create()) {
    // Companion
    companion object {
        @JvmStatic external fun create(): Long
    }

    // Propriétés
    var indices: IntArray
        external get
        external set

    var vertices: VectorArray3f
        get() = VectorArray3f(ngetVertices())
        set(v) { nsetVertices(v) }

    // Methodes
    external fun init(vattr: VertexAttribute)
    external fun draw()

    private external fun ngetVertices(): Long
    private external fun nsetVertices(vertices: VectorArray3f)
}