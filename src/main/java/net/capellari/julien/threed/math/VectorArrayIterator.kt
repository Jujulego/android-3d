package net.capellari.julien.threed.math

class VectorArrayIterator<V: Vector<*,*>>(val array: VectorArray<V>, private var index: Int) : ListIterator<V> {
    // Methods
    override fun hasPrevious() = index > 0
    override fun hasNext()     = index < array.size - 1

    override fun previous() = array[--index]
    override fun next()     = array[index++]

    override fun previousIndex() = index - 1
    override fun nextIndex()     = index + 1
}