package net.capellari.julien.threed.gpu

import android.opengl.GLES32

// Enums
enum class Target(val gl: Int) {
    ARRAY_BUFFER(GLES32.GL_ARRAY_BUFFER),
    ELEMENT_ARRAY_BUFFER(GLES32.GL_ELEMENT_ARRAY_BUFFER)
}