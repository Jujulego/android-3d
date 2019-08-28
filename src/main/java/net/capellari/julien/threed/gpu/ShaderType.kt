package net.capellari.julien.threed.gpu

import android.opengl.GLES32

enum class ShaderType(val gl: Int) {
    VERTEX(GLES32.GL_VERTEX_SHADER),
    GEOMETRY(GLES32.GL_GEOMETRY_SHADER),
    FRAGMENT(GLES32.GL_FRAGMENT_SHADER)
}