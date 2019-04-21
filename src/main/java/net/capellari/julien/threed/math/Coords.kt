package net.capellari.julien.threed.math

interface Coords<T: Number, D: Dimension> {
    // Opérateurs
    operator fun get(i: Int): T
    operator fun set(i: Int, v: T)

    operator fun unaryPlus(): Coords<T,D>
    operator fun unaryMinus(): Coords<T,D>

    // Méthodes
    fun size(): Int
}