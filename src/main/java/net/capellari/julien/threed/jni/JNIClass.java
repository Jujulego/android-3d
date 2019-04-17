package net.capellari.julien.threed.jni;

abstract class JNIClass {
    // Initialisation classe
    static {
        System.loadLibrary("android3d");
    }

    // Attributs
    @SuppressWarnings("unused,FieldCanBeLocal")
    private final long nativeHandle;

    // Constructeur
    protected JNIClass(long handle) {
        nativeHandle = handle;
        acquire();
    }

    // Méthodes
    private native boolean acquire();
    public native void dispose();
}
