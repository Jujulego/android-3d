package net.capellari.julien.threed.gpu

interface Uniformable {
    // Methods
    fun toUniform(location: Int)
}

interface NativeUniformable: Uniformable {
    /**
     * Indicates the object is Uniformable on a native level
     */

    // Methods
    override fun toUniform(location: Int) {
        TODO("not implemented")
    }
}