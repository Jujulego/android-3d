package net.capellari.julien.threed

import net.capellari.julien.threed.jni.JNIClass
import net.capellari.julien.threed.math.*

class Point2f: JNIClass, Point<Float,D2> {
    // Companion
    companion object {
        // Méthodes
        @JvmStatic
        private external fun create(x: Float = 0f, y: Float = 0f): Long
    }

    // Propriétés
    var x by coordX()
    var y by coordY()

    // Constructeurs
    internal constructor(handle: Long): super(handle)

    constructor(): this(create())
    constructor(x: Float, y: Float): this(create(x, y))

    // Opérateurs
    override operator fun get(i: Int)           = getCoord(i)
    override operator fun set(i: Int, v: Float) = setCoord(i, v)

    override fun unaryPlus()  = Point2f(+x, +y)
    override fun unaryMinus() = Point2f(-x, -y)

    override fun plusAssign(v: Vector<Float, D2>) {
        x += v[0]
        y += v[1]
    }
    override fun minusAssign(v: Vector<Float, D2>) {
        x -= v[0]
        y -= v[1]
    }

    override fun plus(v: Vector<Float, D2>)  = Point2f(x + v[0], y + v[1])
    override fun minus(v: Vector<Float, D2>) = Point2f(x - v[0], y - v[1])

    override fun minus(pt: Point<Float, D2>) = Vec2f(x - pt[0], y - pt[1])

    // Méthodes
    override fun size()= D2.size
    override fun equals(other: Any?): Boolean {
        if (other is Point2f) {
            return equal(other)
        }

        return false
    }
    override fun hashCode(): Int {
        return ((x * 32) + y).toInt()
    }

    override fun toString(): String {
        return "Point($x, $y)"
    }

    // Méthodes natives
    private external fun getCoord(i: Int): Float
    private external fun setCoord(i: Int, v: Float)

    private external fun equal(other: Point2f): Boolean
}