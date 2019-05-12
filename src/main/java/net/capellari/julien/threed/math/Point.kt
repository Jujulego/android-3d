package net.capellari.julien.threed.math

interface Point<T: Number, D: Dimension>: Coord<T, D> {
    // Opérateurs
    operator fun plusAssign(v: Vector<T,D>)
    operator fun minusAssign(v: Vector<T,D>)

    operator fun plus(v: Vector<T,D>): Point<T,D>
    operator fun minus(v: Vector<T,D>): Point<T,D>

    operator fun minus(pt: Point<T,D>): Vector<T,D>
}