package net.capellari.julien.threed

import net.capellari.julien.threed.jni.JNIClass
import net.capellari.julien.threed.math.*
import net.capellari.julien.threed.math.coords.XY

class Point2i: JNIClass, XY<Int>, Point<Int,D2> {
    // Companion
    companion object {
        // Méthodes
        @JvmStatic private external fun create(x: Int, y: Int): Long
        @JvmStatic private external fun createA(factors: IntArray): Long
        @JvmStatic private external fun createC(pt: Point2i): Long
    }

    // Propriétés
    val data get() = getDataA()

    // Constructeurs
    internal constructor(handle: Long): super(handle)

    constructor(pt: Point2i): this(createC(pt))
    constructor(x: Int = 0, y: Int = 0): this(create(x, y))

    constructor(factors: IntArray): this(createA(factors))
    constructor(gen: (Int) -> Int): this(IntArray(2, gen))

    // Opérateurs
    override operator fun get(i: Int)         = getCoord(i)
    override operator fun set(i: Int, v: Int) = setCoord(i, v)

    override fun unaryPlus()  = Point2i(this)
    override fun unaryMinus() = Point2i(-x, -y)

    override fun plusAssign(v: Vector<Int, D2>) {
        x += v[0]
        y += v[1]
    }
    override fun minusAssign(v: Vector<Int, D2>) {
        x -= v[0]
        y -= v[1]
    }

    override fun plus(v: Vector<Int, D2>)  = Point2i(x + v[0], y + v[1])
    override fun minus(v: Vector<Int, D2>) = Point2i(x - v[0], y - v[1])

    override fun minus(pt: Point<Int, D2>) = Vec2i(x - pt[0], y - pt[1])
    override fun times(c: Coord<Int, D2>) = (x * c[0]) + (y * c[1])

    // Méthodes
    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other is Point2i) {
            return equal(other)
        }

        return super.equals(other)
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }

    override fun toString(): String {
        return "Point($x, $y)"
    }

    // Méthodes natives
    private external fun getDataA(): IntArray
    private external fun getCoord(i: Int): Int
    private external fun setCoord(i: Int, v: Int)

    private external fun equal(other: Point2i): Boolean
}