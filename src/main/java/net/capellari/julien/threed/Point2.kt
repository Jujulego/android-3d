package net.capellari.julien.threed

import net.capellari.julien.threed.math.Point
import net.capellari.julien.threed.math.Two
import net.capellari.julien.threed.math.Vector
import net.capellari.julien.threed.math.coords.XY

class Point2: XY, Point<Two> {
    // Constructeurs
    constructor(): super()
    constructor(x: Float, y: Float): super(x, y)

    // Méthodes
    override fun toString() = "Point($x, $y)"
    override fun newPoint() = Point2()
    override fun newVector() = Vec2()
}