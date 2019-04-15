package net.capellari.julien.threed.math.coords

import net.capellari.julien.threed.math.Three
import net.capellari.julien.threed.math.bases.FloatCoords

open class RGB(): FloatCoords<Three>(Three) {
    // Propriétés
    var r get() = data[0]
        set(value) { data[0] = value }

    var g get() = data[1]
        set(value) { data[1] = value }

    var b get() = data[2]
        set(value) { data[2] = value }

    // Constructeurs
    constructor(r: Float, g: Float, b: Float): this() {
        this.r = r
        this.g = g
        this.b = b
    }
}