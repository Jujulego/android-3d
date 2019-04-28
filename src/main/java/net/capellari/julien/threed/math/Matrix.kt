package net.capellari.julien.threed.math

interface Matrix<T: Number, L: Dimension, C: Dimension> {
    // Opérateurs
    operator fun get(c: Int, l: Int): T
    operator fun set(c: Int, l: Int, v: T)

    operator fun unaryPlus(): Matrix<T, L, C>
    operator fun unaryMinus(): Matrix<T, L, C>

    operator fun plusAssign(mat: Matrix<T,L,C>)
    operator fun minusAssign(mat: Matrix<T,L,C>)
    operator fun timesAssign(k: T)
    operator fun divAssign(k: T)

    operator fun plus(mat: Matrix<T,L,C>): Matrix<T,L,C>
    operator fun minus(mat: Matrix<T,L,C>): Matrix<T,L,C>
    operator fun times(k: T): Matrix<T,L,C>
    operator fun div(k: T): Matrix<T,L,C>

    // Méthodes
    fun lig(l: Int): Vector<T,C>
    fun col(c: Int): Vector<T,L>

    fun size(): MatSize<L,C>
}