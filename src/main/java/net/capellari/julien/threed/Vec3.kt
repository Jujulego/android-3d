package net.capellari.julien.threed

import net.capellari.julien.threed.math.*
import kotlin.math.sqrt

class Vec3: XYZ, Vector<Three> {
    // Constructeurs
    constructor(): super()
    constructor(xy: XY, z: Float): super(xy, z)
    constructor(x: Float, y: Float, z: Float): super(x, y, z)

    // Opérateurs
    override operator fun unaryPlus()  = Vec3(x, y, z)
    override operator fun unaryMinus() = Vec3(-x, -y, -z)

    override operator fun plus(v: Vector<Three>) = Vec3(x + v[0], y + v[1], z + v[2])
    override operator fun plus(pt: Point<Three>) = Point3(x + pt[0], y + pt[1], z + pt[2])

    override operator fun minus(v: Vector<Three>) = Vec3(x - v[0], y - v[1], z - v[2])
    override operator fun minus(pt: Point<Three>) = Point3(x - pt[0], y - pt[1], z - pt[2])

    override operator fun times(k: Float) = Vec3(k * x, k * y, k * z)
    override operator fun div(k: Float)   = Vec3(x / k, y / k, z / k)

    // Méthodes
    override fun toString() = "Vec($x, $y, $z)"

    infix fun vect(v: Vector<Three>)
            = Vec3(y * v[2] - z * v[1], z * v[0] - x * v[2], x * v[1] - y * v[0])
}