package net.capellari.julien.threed.gpu

import android.opengl.GLES32

enum class Usage(val gl: Int) {
    STATIC_DRAW(GLES32.GL_STATIC_DRAW),
    DYNAMIC_DRAW(GLES32.GL_DYNAMIC_DRAW),
    STREAM_DRAW(GLES32.GL_STREAM_DRAW)
}