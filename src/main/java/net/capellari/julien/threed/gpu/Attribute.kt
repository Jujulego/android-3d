package net.capellari.julien.threed.gpu

import net.capellari.julien.threed.jni.JNIClass

class Attribute: JNIClass {
    // Companion
    companion object {
        // Static methods
        @JvmStatic external fun create(location: Int, type: Int, size: Int): Long
        @JvmStatic external fun create(name: String, type: Int, size: Int): Long
    }

    // Constructors
    constructor(index: Int, type: Type, size: Int): super(create(index, type.gl, size))
    constructor(name: String, type: Type, size: Int): super(create(name, type.gl, size))

    // Methods
    external fun prepare(program: Program)
    external fun enable(stride: Int, offset: Int = 0, normalized: Boolean = false)
}