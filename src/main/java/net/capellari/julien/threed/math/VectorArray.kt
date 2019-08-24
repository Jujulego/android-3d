package net.capellari.julien.threed.math

interface VectorArray<V: Vector<*,*>>: Collection<V> {
    // Properties
    override val size: Int

    // Operators
    operator fun get(i: Int): V
    operator fun set(i: Int, element: V)

    // Methods
    override fun contains(element: V): Boolean

    fun add(element: V): Boolean
    fun remove(element: V): Boolean

    // Defaulted methods
    override fun isEmpty() = size == 0

    // - iterators
    override fun iterator(): Iterator<V> = listIterator(0)
    fun listIterator(): ListIterator<V> = listIterator(0)
    fun listIterator(index: Int): ListIterator<V> = VectorArrayIterator(this, index)

    // - bulk
    override fun containsAll(elements: Collection<V>): Boolean {
        return elements.all { contains(it) }
    }
}