package net.capellari.julien.threed.math

interface Vector<T: Number, D: Dimension>: Coord<T, D> {
    // Opérateurs
    operator fun plusAssign(v: Vector<T,D>)
    operator fun plus(v: Vector<T,D>): Vector<T,D>

    operator fun minusAssign(v: Vector<T,D>)
    operator fun minus(v: Vector<T,D>): Vector<T,D>

    operator fun timesAssign(k: T)
    operator fun times(k: T): Vector<T,D>

    operator fun divAssign(k: T)
    operator fun div(k: T): Vector<T,D>

    operator fun times(mat: Matrix<T,D,D>): Vector<T,D>
}