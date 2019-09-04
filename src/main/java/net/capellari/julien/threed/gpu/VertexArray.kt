package net.capellari.julien.threed.gpu

import net.capellari.julien.threed.jni.JNIClass

class VertexArray: JNIClass(create()) {
    // Companion
    companion object {
        @JvmStatic external fun create(): Long
    }

    // Methods
    external fun bind()

    // - utils
    fun bind(cb: () -> Unit) {
        bind()
        cb()
    }
}