package net.capellari.julien.threed

import net.capellari.julien.threed.jni.JNIClass
import net.capellari.julien.threed.jni.Points

class Point2i: JNIClass {
    // Propriétés
    val x: Int get() = Points.getCoord(this, 0)
    val y: Int get() = Points.getCoord(this, 1)

    // Constructeurs
    constructor(): super(Points.createPoint_i2(0, 0))
    constructor(x: Int, y: Int): super(Points.createPoint_i2(x, y))
}