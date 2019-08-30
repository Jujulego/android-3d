package net.capellari.julien.threed.gpu

interface Bufferable {
    // Methods
    fun getBufferSize(): Int
    fun getData(): ByteArray
}