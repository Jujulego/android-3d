package net.capellari.julien.threed

import net.capellari.julien.threed.math.*
import net.capellari.julien.threed.math.coords.XY
import net.capellari.julien.threed.math.coords.XYZ

class Point3: XYZ, Point<Three> {
    // Constructeurs
    constructor(): super()
    constructor(xy: XY, z: Float): super(xy, z)
    constructor(x: Float, y: Float, z: Float): super(x, y, z)

    // Méthodes
    override fun toString() = "Point($x, $y, $z)"
    override fun newPoint() = Point3()
    override fun newVector() = Vec3()
}