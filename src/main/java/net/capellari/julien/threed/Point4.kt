package net.capellari.julien.threed

import net.capellari.julien.threed.math.*
import net.capellari.julien.threed.math.coords.XY
import net.capellari.julien.threed.math.coords.XYZ
import net.capellari.julien.threed.math.coords.XYZA

class Point4: XYZA, Point<Four> {
    // Constructeurs
    constructor(): super()
    constructor(xyz: XYZ, a: Float): super(xyz, a)
    constructor(xy: XY, z: Float, a: Float): super(xy, z, a)
    constructor(x: Float, y: Float, z: Float, a: Float): super(x, y, z, a)

    // Méthodes
    override fun toString() = "Point($x, $y, $z, $a)"
    override fun newPoint() = Point4()
    override fun newVector() = Vec4()
}