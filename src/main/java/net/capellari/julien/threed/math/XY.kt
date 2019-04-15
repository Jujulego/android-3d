package net.capellari.julien.threed.math

open class XY(): FloatCoords<Two>(Two) {
    // Propriétés
    var x get() = data[0]
        set(value) { data[0] = value }

    var y get() = data[1]
        set(value) { data[1] = value }

    override val degres: Int = 2

    // Constructeurs
    constructor(x: Float, y: Float): this() {
        this.x = x
        this.y = y
    }
}