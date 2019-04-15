package net.capellari.julien.threed

import net.capellari.julien.threed.math.*
import kotlin.math.sqrt

class Vec4: XYZA, Vector<Four> {
    // Constructeurs
    constructor(): super()
    constructor(xyz: XYZ, a: Float): super(xyz, a)
    constructor(xy: XY, z: Float, a: Float): super(xy, z, a)
    constructor(x: Float, y: Float, z: Float, a: Float): super(x, y, z, a)

    // Opérateurs
    override operator fun unaryPlus()  = Vec4(x, y, z, a)
    override operator fun unaryMinus() = Vec4(-x, -y, -z, -a)

    override operator fun plus(v: Vector<Four>) = Vec4(  x +  v[0], y +  v[1], z +  v[2], a +  v[3])
    override operator fun plus(pt: Point<Four>) = Point4(x + pt[0], y + pt[1], z + pt[2], a + pt[3])

    override operator fun minus(v: Vector<Four>) = Vec4(  x -  v[0], y -  v[1], z -  v[2], a -  v[3])
    override operator fun minus(pt: Point<Four>) = Point4(x - pt[0], y - pt[1], z - pt[2], a - pt[3])

    override operator fun times(k: Float) = Vec4(x * k, y * k, z * k, a * k)
    override operator fun div(k: Float)   = Vec4(x / k, y / k, z / k, a / k)

    // Méthodes
    override fun toString() = "Vec($x, $y, $z, $a)"
}