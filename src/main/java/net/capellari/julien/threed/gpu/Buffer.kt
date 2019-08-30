package net.capellari.julien.threed.gpu

import android.opengl.GLES32
import net.capellari.julien.threed.jni.JNIClass

class Buffer: JNIClass(create()) {
    // Companion
    companion object {
        // Static methods
        @JvmStatic private external fun create(): Long

        // Enums
        enum class Target(val gl: Int) {
            ARRAY_BUFFER(GLES32.GL_ARRAY_BUFFER)
        }

        enum class Usage(val gl: Int) {
            STATIC_DRAW(GLES32.GL_STATIC_DRAW),
            DYNAMIC_DRAW(GLES32.GL_DYNAMIC_DRAW),
            STREAM_DRAW(GLES32.GL_STREAM_DRAW)
        }
    }

    // Methods
    external fun regenerate()

    fun bound(target: Target) = nbound(target.gl)
    private external fun nbound(target: Int)

    fun setData(data: Bufferable, usage: Usage) {
        if (data is NativeBufferable) {
            setNData(data, usage.gl)
        } else {
            setJData(data, usage.gl)
        }
    }
    private external fun setNData(data: NativeBufferable, usage: Int)
    private external fun setJData(data: Bufferable, usage: Int)

    external fun unbound()
}