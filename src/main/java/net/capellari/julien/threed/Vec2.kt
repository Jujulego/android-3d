package net.capellari.julien.threed

import net.capellari.julien.threed.math.Two
import net.capellari.julien.threed.math.Vector
import net.capellari.julien.threed.math.coords.XY

class Vec2: XY, Vector<Two> {
    // Constructeurs
    constructor(): super()
    constructor(x: Float, y: Float): super(x, y)

    // Méthodes
    override fun newPoint() = Point2()
    override fun newVector() = Vec2()
    override fun toString() = "Vec($x, $y)"
}