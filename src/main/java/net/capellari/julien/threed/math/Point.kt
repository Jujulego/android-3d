package net.capellari.julien.threed.math

import net.capellari.julien.threed.annotations.math.NumberType
import net.capellari.julien.threed.annotations.math.PointClass

@PointClass(NumberType.INT, 2)
interface Point<T: Number, D: Dimension>: Coord<T, D> {
    // Opérateurs
    operator fun plusAssign(v: Vector<T,D>)
    operator fun minusAssign(v: Vector<T,D>)

    operator fun plus(v: Vector<T,D>): Point<T,D>
    operator fun minus(v: Vector<T,D>): Point<T,D>

    operator fun minus(pt: Point<T,D>): Vector<T,D>
}