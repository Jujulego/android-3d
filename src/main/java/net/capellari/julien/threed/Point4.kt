package net.capellari.julien.threed

import net.capellari.julien.threed.math.*

class Point4: XYZA, Point<Four> {
    // Constructeurs
    constructor(): super()
    constructor(xyz: XYZ, a: Float): super(xyz, a)
    constructor(xy: XY, z: Float, a: Float): super(xy, z, a)
    constructor(x: Float, y: Float, z: Float, a: Float): super(x, y, z, a)

    // Opérateurs
    override operator fun unaryPlus()  = Point4(x, y, z, a)
    override operator fun unaryMinus() = Point4(-x, -y, -z, -a)

    override operator fun plus(v: Vector<Four>) = Point4(x + v[0], y + v[1], z + v[2], a + v[3])

    override operator fun minus(pt: Point<Four>) = Vec4(x - pt[0], y - pt[1], z - pt[2], a - pt[3])
    override operator fun minus(v: Vector<Four>) = Point4(x - v[0], y - v[1], z - v[2], a - v[3])

    // Méthodes
    override fun toString() = "Point($x, $y, $z, $a)"
}