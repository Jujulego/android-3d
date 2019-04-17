package net.capellari.julien.threed.math.coords

import net.capellari.julien.threed.math.Four
import net.capellari.julien.threed.math.bases.FloatCoords

abstract class XYZA(): FloatCoords<Four>(Four) {
    // Propriétés
    var x get() = data[0]
        set(value) { data[0] = value }

    var y get() = data[1]
        set(value) { data[1] = value }

    var z get() = data[2]
        set(value) { data[2] = value }

    var a get() = data[3]
        set(value) { data[3] = value }

    // Constructeurs
    constructor(x: Float, y: Float, z: Float, a: Float): this() {
        this.x = x
        this.y = y
        this.z = z
        this.a = a
    }

    constructor(xy: XY, z: Float, a: Float): this() {
        this.x = xy.x
        this.y = xy.y
        this.z = z
        this.a = a
    }

    constructor(xyz: XYZ, a: Float): this() {
        this.x = xyz.x
        this.y = xyz.y
        this.z = xyz.z
        this.a = a
    }
}