package net.capellari.julien.threed.jni

class JNIRegister<T: JNIClass>(private val factory: (Long) -> T) {
    // Attributs
    private val register = mutableMapOf<Long,T>()

    // Methods
    fun add(obj: T) {
        register[obj.nativeHandle] = obj
    }

    fun get(handle: Long): T {
        var obj = register[handle]

        if (obj == null) {
            obj = factory(handle)
            register[handle] = obj
        }

        return obj
    }
}