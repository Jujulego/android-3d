package net.capellari.julien.threed.math

interface Vector<T: Number, D: Dimension>: Coords<T, D> {
    // Opérateurs
    operator fun plus(v: Vector<T,D>): Vector<T,D>
    operator fun plusAssign(v: Vector<T,D>)

    operator fun minus(v: Vector<T,D>): Vector<T,D>
    operator fun minusAssign(v: Vector<T,D>)
}