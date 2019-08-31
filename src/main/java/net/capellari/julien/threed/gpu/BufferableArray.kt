package net.capellari.julien.threed.gpu

interface BufferableArray {
    // Methods
    fun getBufferSize(): Int
    fun getBufferElementCount(): Int
    fun getBufferElement(i: Int): Bufferable
}

interface NativeBufferableArray: BufferableArray {
    /**
     * Indicates the object is BufferableArray on a native level
     */

    // Methods
    override fun getBufferSize(): Int {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun getBufferElementCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBufferElement(i: Int): Bufferable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}