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

    fun bind(target: Target) = nbind(target.gl)
    private external fun nbind(target: Int)

    fun setData(data: Bufferable, usage: Usage) {
        if (data is NativeBufferable) {
            setNData(data, usage.gl)
        } else {
            setJData(data, usage.gl)
        }
    }
    private external fun setNData(data: NativeBufferable, usage: Int)
    private external fun setJData(data: Bufferable, usage: Int)

    fun setData(data: IntArray, usage: Usage) {
        setJData(data, usage.gl)
    }
    private external fun setJData(data: IntArray, usage: Int)

    fun setDataArray(data: BufferableArray, usage: Usage) {
        if (data is NativeBufferableArray) {
            setNDataArray(data, usage.gl)
        } else {
            setJDataArray(data, usage.gl)
        }
    }
    private external fun setNDataArray(data: NativeBufferableArray, usage: Int)
    private external fun setJDataArray(data: BufferableArray, usage: Int)

    external fun unbind()
}