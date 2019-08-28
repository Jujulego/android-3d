package net.capellari.julien.threed.gpu

import net.capellari.julien.threed.jni.JNIClass

class Program: JNIClass(create()) {
    // Companion
    companion object {
        @JvmStatic private external fun create(): Long
    }

    // Methods
    external fun addShader(shader: Shader)

    external fun compile()
    external fun use()
    external fun destroy()
}