package net.capellari.julien.threed

import net.capellari.julien.threed.math.*
import net.capellari.julien.threed.math.coords.XY
import net.capellari.julien.threed.math.coords.XYZ

class Vec3: XYZ, Vector<Three> {
    // Constructeurs
    constructor(): super()
    constructor(xy: XY, z: Float): super(xy, z)
    constructor(x: Float, y: Float, z: Float): super(x, y, z)

    // Méthodes
    override fun newPoint() = Point3()
    override fun newVector() = Vec3()
    override fun toString() = "Vec($x, $y, $z)"

    infix fun vect(v: Vector<Three>)
            = Vec3(y * v[2] - z * v[1], z * v[0] - x * v[2], x * v[1] - y * v[0])
}