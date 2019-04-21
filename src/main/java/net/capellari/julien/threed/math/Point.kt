package net.capellari.julien.threed.math

interface Point<T: Number, D: Dimension>: Coords<T, D> {
    // Opérateurs
    operator fun minus(pt: Point<T,D>): Vector<T,D>
}