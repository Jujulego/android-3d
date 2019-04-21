package net.capellari.julien.threed.math

interface Point<T: Number, D: Dimension>: Coords<T, D> {
    // Opérateurs
    operator fun plus(pt: Point<T,D>): Point<T,D>
    operator fun plusAssign(pt: Point<T,D>)

    operator fun minus(pt: Point<T,D>): Point<T,D>
    operator fun minusAssign(pt: Point<T,D>)
}