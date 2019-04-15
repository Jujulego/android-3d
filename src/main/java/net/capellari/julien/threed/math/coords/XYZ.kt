package net.capellari.julien.threed.math.coords

import net.capellari.julien.threed.math.Three
import net.capellari.julien.threed.math.bases.FloatCoords

open class XYZ(): FloatCoords<Three>(Three) {
    // Propriétés
    var x get() = data[0]
        set(value) { data[0] = value }

    var y get() = data[1]
        set(value) { data[1] = value }

    var z get() = data[2]
        set(value) { data[2] = value }

    // Constructeurs
    constructor(x: Float, y: Float, z: Float): this() {
        this.x = x
        this.y = y
        this.z = z
    }

    constructor(xy: XY, z: Float): this() {
        this.x = xy.x
        this.y = xy.y
        this.z = z
    }
}