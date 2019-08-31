package net.capellari.julien.threed.gpu

interface Bufferable {
    // Methods
    fun getBufferSize(): Int
    fun getData(): ByteArray
}

interface NativeBufferable: Bufferable {
    /**
     * Indicates the object is Bufferable on a native level
     */

    // Methods
    override fun getBufferSize(): Int {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getData(): ByteArray {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}