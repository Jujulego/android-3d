package net.capellari.julien.threed.gpu

import net.capellari.julien.threed.jni.JNIClass

class Uniform internal constructor(handle: Long): JNIClass(handle) {
    // Companion
    companion object {
        @JvmStatic external fun create(name: String, program: Program): Long
    }

    // Constructor
    constructor(name: String, program: Program): this(create(name, program))

    // Methods
    fun setValue(data: Uniformable) {
        if (data is NativeUniformable) {
            setNValue(data)
        } else {
            setJValue(data)
        }
    }

    private external fun setNValue(data: NativeUniformable)
    private external fun setJValue(data: Uniformable)
}