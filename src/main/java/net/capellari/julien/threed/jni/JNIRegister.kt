package net.capellari.julien.threed.jni

import java.lang.ref.WeakReference

class JNIRegister<T: JNIClass>(private val factory: (Long) -> T) {
    // Attributs
    private val register = mutableMapOf<Long,WeakReference<T>>()

    // Methods
    fun add(obj: T) {
        register[obj.nativeHandle] = WeakReference(obj)
    }

    fun get(handle: Long): T {
        var obj = register[handle]?.get()

        if (obj == null) {
            obj = factory(handle)
            register[handle] = WeakReference(obj)
        }

        return obj
    }
}