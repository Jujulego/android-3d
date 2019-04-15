package net.capellari.julien.threed

import net.capellari.julien.threed.math.*

class Point3: XYZ, Point<Three> {
    // Constructeurs
    constructor(): super()
    constructor(xy: XY, z: Float): super(xy, z)
    constructor(x: Float, y: Float, z: Float): super(x, y, z)

    // Opérateurs
    override operator fun unaryPlus()  = Point3(x, y, z)
    override operator fun unaryMinus() = Point3(-x, -y, -z)

    override operator fun plus(v: Vector<Three>) = Point3(x + v[0], y + v[1], z + v[2])

    override operator fun minus(pt: Point<Three>) = Vec3(x - pt[0], y - pt[1], z - pt[2])
    override operator fun minus(v: Vector<Three>) = Point3(x - v[0], y - v[1], z - v[2])

    // Méthodes
    override fun toString() = "Point($x, $y, $z)"
}