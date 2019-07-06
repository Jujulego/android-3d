package net.capellari.julien.threed.math

interface VectorArray<V: Vector<*,*>> {
    // Properties
    val size: Int

    // Operators
    operator fun get(i: Int): V
    operator fun set(i: Int, value: V)

    // Methods
    fun add(element: V): Boolean
}