package net.capellari.julien.threed.math.coords

import net.capellari.julien.threed.math.Four
import net.capellari.julien.threed.math.bases.FloatCoords

abstract class RGBA(): FloatCoords<Four>(Four) {
    // Propriétés
    var r get() = data[0]
        set(value) { data[0] = value }

    var g get() = data[1]
        set(value) { data[1] = value }

    var b get() = data[2]
        set(value) { data[2] = value }

    var a get() = data[3]
        set(value) { data[3] = value }

    // Constructeurs
    constructor(r: Float, g: Float, b: Float, a: Float): this() {
        this.r = r
        this.g = g
        this.b = b
        this.a = a
    }

    constructor(rgb: RGB, a: Float): this() {
        this.r = rgb.r
        this.g = rgb.g
        this.b = rgb.b
        this.a = a
    }
}