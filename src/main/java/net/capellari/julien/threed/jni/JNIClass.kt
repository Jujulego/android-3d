package net.capellari.julien.threed.jni

abstract class JNIClass {
    // Companion
    companion object {
        // Initialisation classe
        init {
            System.loadLibrary("android3d")
        }
    }

    // Attributs
    @JvmField
    val nativeHandle: Long

    // Constructeur
    protected constructor(nativeHandle: Long) {
        this.nativeHandle = nativeHandle
        assert(nativeHandle != 0L)
        assert(acquire())
    }

    // Méthodes
    private external fun acquire(): Boolean
    external fun dispose()
}
