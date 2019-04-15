package net.capellari.julien.threed

import net.capellari.julien.threed.math.Point
import net.capellari.julien.threed.math.Two
import net.capellari.julien.threed.math.Vector
import net.capellari.julien.threed.math.XY

class Point2: XY, Point<Two> {
    // Constructeurs
    constructor(): super()
    constructor(x: Float, y: Float): super(x, y)

    // Opérateurs
    override operator fun unaryPlus()  = Point2(x, y)
    override operator fun unaryMinus() = Point2(-x, -y)

    override operator fun plus(v: Vector<Two>) = Point2(x + v[0], y + v[1])

    override operator fun minus(pt: Point<Two>) = Vec2(x - pt[0], y - pt[1])
    override operator fun minus(v: Vector<Two>) = Point2(x - v[0], y - v[1])

    // Méthodes
    override fun toString() = "Point($x, $y)"
}