package net.capellari.julien.threed.math

interface Coord<T: Number, D: Dimension> {
    // Opérateurs
    operator fun get(i: Int): T
    operator fun set(i: Int, v: T)

    operator fun unaryPlus(): Coord<T,D>
    operator fun unaryMinus(): Coord<T,D>

    operator fun times(c: Coord<T,D>): T // dot product

    // Méthodes
    fun size(): Int

    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}