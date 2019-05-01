package net.capellari.julien.threed.math

interface Coords<T: Number, D: Dimension> {
    // Opérateurs
    operator fun get(i: Int): T
    operator fun set(i: Int, v: T)

    operator fun unaryPlus(): Coords<T,D>
    operator fun unaryMinus(): Coords<T,D>

    operator fun times(c: Coords<T,D>): T // dot product

    // Méthodes
    fun size(): Int
}