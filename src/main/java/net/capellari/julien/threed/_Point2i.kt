package net.capellari.julien.threed

import net.capellari.julien.threed.jni.JNIClass
import net.capellari.julien.threed.math.*

class _Point2i: JNIClass, Point<Int,D2> {
    // Companion
    companion object {
        // Méthodes
        @JvmStatic
        private external fun create(x: Int = 0, y: Int = 0): Long
    }

    // Propriétés
    var x by coordX()
    var y by coordY()

    override val size = D2.size

    // Constructeurs
    internal constructor(handle: Long): super(handle)

    constructor(): this(create())
    constructor(x: Int, y: Int): this(create(x, y))

    // Opérateurs
    override operator fun get(i: Int)         = getCoord(i)
    override operator fun set(i: Int, v: Int) = setCoord(i, v)

    override fun unaryPlus()  = _Point2i(+x, +y)
    override fun unaryMinus() = _Point2i(-x, -y)

    override fun plusAssign(v: Vector<Int, D2>) {
        x += v[0]
        y += v[1]
    }
    override fun minusAssign(v: Vector<Int, D2>) {
        x -= v[0]
        y -= v[1]
    }

    override fun plus(v: Vector<Int, D2>)  = _Point2i(x + v[0], y + v[1])
    override fun minus(v: Vector<Int, D2>) = _Point2i(x - v[0], y - v[1])

    override fun minus(pt: Point<Int, D2>) = Vec2i(x - pt[0], y - pt[1])

    // Méthodes
    override fun equals(other: Any?): Boolean {
        if (other is _Point2i) {
            return equal(other)
        }

        return false
    }
    override fun hashCode(): Int {
        return (x shl 32) + y
    }

    override fun toString(): String {
        return "Point($x, $y)"
    }

    // Méthodes natives
    private external fun getCoord(i: Int): Int
    private external fun setCoord(i: Int, v: Int)

    private external fun equal(other: _Point2i): Boolean
}