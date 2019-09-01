package net.capellari.julien.threed.gpu

import android.opengl.GLES32

enum class Type(val gl: Int) {
    INT(GLES32.GL_INT),
    FLOAT(GLES32.GL_FLOAT)
}