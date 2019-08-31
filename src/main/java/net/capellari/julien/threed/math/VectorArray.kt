package net.capellari.julien.threed.math

import net.capellari.julien.threed.gpu.NativeBufferableArray

interface VectorArray<V: Vector<*,*>>: MutableList<V>, NativeBufferableArray {
    // Properties
    override val size: Int

    // Operators
    override operator fun get(index: Int): V
    override operator fun set(index: Int, element: V): V

    // Methods
    override fun indexOf(element: V): Int
    override fun lastIndexOf(element: V): Int

    override fun add(element: V): Boolean
    override fun add(index: Int, element: V)

    override fun remove(element: V): Boolean
    override fun removeAt(index: Int): V
    override fun clear()

    // Defaulted methods
    override fun isEmpty() = size == 0
    override fun contains(element: V) = indexOf(element) != -1

    // - iterators
    override fun iterator(): MutableIterator<V> = listIterator(0)
    override fun listIterator(): MutableListIterator<V> = listIterator(0)
    override fun listIterator(index: Int): MutableListIterator<V> = VectorArrayIterator(this, index)

    // - bulk
    override fun containsAll(elements: Collection<V>)
            = elements.all { contains(it) }

    override fun addAll(elements: Collection<V>)
            = elements.map { add(it) }.reduce(Boolean::or)

    override fun addAll(index: Int, elements: Collection<V>): Boolean {
        elements.forEachIndexed { i: Int, v: V -> add(index + i, v) }
        return true
    }

    override fun removeAll(elements: Collection<V>)
            = elements.map { remove(it) }.reduce(Boolean::or)

    override fun retainAll(elements: Collection<V>)
            = removeAll(filter { !elements.contains(it) })

    override fun subList(fromIndex: Int, toIndex: Int): VectorArray<V>
}