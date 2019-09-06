package net.capellari.julien.threed.math

class VectorArrayIterator<V: Vector<*,*>>(val array: VectorArray<V>, private var index: Int) : MutableListIterator<V> {
    // Methods
    override fun hasPrevious() = index > 0
    override fun hasNext()     = index < array.size

    override fun previous() = array[--index]
    override fun next()     = array[index++]

    override fun previousIndex() = index - 1
    override fun nextIndex()     = index + 1

    // - modifications
    override fun add(element: V) {
        array.add(index, element)
    }

    override fun set(element: V) {
        array[index] = element
    }

    override fun remove() {
        array.remove(array[index])
    }
}