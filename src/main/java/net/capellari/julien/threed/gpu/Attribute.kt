package net.capellari.julien.threed.gpu

import net.capellari.julien.threed.jni.JNIClass

class Attribute: JNIClass {
    // Companion
    companion object {
        // Static methods
        @JvmStatic external fun create(index: Int): Long
        @JvmStatic external fun create(name: String): Long
    }

    // Constructors
    constructor(index: Int): super(create(index))
    constructor(name: String): super(create(name))

    // Methods
    external fun prepare(program: Program)
}