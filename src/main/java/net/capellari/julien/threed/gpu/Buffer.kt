package net.capellari.julien.threed.gpu

import net.capellari.julien.threed.jni.JNIClass

class Buffer: JNIClass(create()) {
    // Companion
    companion object {
        // Static methods
        @JvmStatic private external fun create(): Long
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

    fun setDataArray(data: BufferableArray, usage: Usage) {
        if (data is NativeBufferableArray) {
            setNDataArray(data, usage.gl)
        } else {
            setJDataArray(data, usage.gl)
        }
    }
    private external fun setNDataArray(data: NativeBufferableArray, usage: Int)
    private external fun setJDataArray(data: BufferableArray, usage: Int)

    external fun unbound()

    // - utils
    fun bound(target: Target, f: (buffer: Buffer) -> Unit) {
        bound(target)
        f(this)
        unbound()
    }

}