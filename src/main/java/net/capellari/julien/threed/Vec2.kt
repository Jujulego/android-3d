package net.capellari.julien.threed

import net.capellari.julien.threed.math.Point
import net.capellari.julien.threed.math.Two
import net.capellari.julien.threed.math.Vector
import net.capellari.julien.threed.math.XY
import kotlin.math.sqrt

class Vec2: XY, Vector<Two> {
    // Constructeurs
    constructor(): super()
    constructor(x: Float, y: Float): super(x, y)

    // Opérateurs
    override operator fun unaryPlus()  = Vec2(x, y)
    override operator fun unaryMinus() = Vec2(-x, -y)

    override operator fun plus(v: Vector<Two>) = Vec2(x + v[0], y + v[1])
    override operator fun plus(pt: Point<Two>) = Point2(x + pt[0], y + pt[1])

    override operator fun minus(v: Vector<Two>) = Vec2(x - v[0], y - v[1])
    override operator fun minus(pt: Point<Two>) = Point2(x - pt[0], y - pt[1])

    override operator fun times(k: Float) = Vec2(k * x, k * y)
    override operator fun div(k: Float)   = Vec2(x / k, y / k)

    // Méthodes
    override fun toString() = "Vec($x, $y)"
}