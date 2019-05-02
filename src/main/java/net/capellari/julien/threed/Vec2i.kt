package net.capellari.julien.threed

import net.capellari.julien.threed.jni.JNIClass
import net.capellari.julien.threed.math.*
import net.capellari.julien.threed.math.coords.XY

class Vec2i: JNIClass, XY<Int>, Vector<Int,D2> {
    // Companion
    companion object {
        // Méthodes
        @JvmStatic private external fun create(x: Int, y: Int): Long
        @JvmStatic private external fun createA(factors: IntArray): Long
    }

    // Propriétés
    val data get() = getDataA()

    // Constructeurs
    internal constructor(handle: Long): super(handle)

    constructor(x: Int = 0, y: Int = 0): this(create(x, y))
    constructor(factors: IntArray): this(createA(factors))
    constructor(gen: (Int) -> Int): this(IntArray(2, gen))

    // Opérateurs
    override operator fun get(i: Int)         = getCoord(i)
    override operator fun set(i: Int, v: Int) = setCoord(i, v)

    override fun unaryPlus()  = Vec2i(+x, +y)
    override fun unaryMinus() = Vec2i(-x, -y)

    override fun plusAssign(v: Vector<Int, D2>) {
        x += v[0]
        y += v[1]
    }
    override fun minusAssign(v: Vector<Int, D2>) {
        x -= v[0]
        y -= v[1]
    }
    override fun timesAssign(k: Int) {
        x *= k
        y *= k
    }
    override fun divAssign(k: Int) {
        x /= k
        y /= k
    }

    override fun plus(v: Vector<Int, D2>)  = Vec2i(x + v[0], y + v[1])
    override fun minus(v: Vector<Int, D2>) = Vec2i(x - v[0], y - v[1])
    override fun times(k: Int)             = Vec2i(x * k, y * k)
    override fun div(k: Int)               = Vec2i(x / k, y / k)

    override fun times(c: Coord<Int, D2>) = (x * c[0]) + (y * c[1])

    // Méthodes
    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other is Vec2i) {
            return equal(other)
        }

        return super.equals(other)
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }

    override fun toString(): String {
        return "Vec($x, $y)"
    }

    // Méthodes natives
    private external fun getDataA(): IntArray
    private external fun getCoord(i: Int): Int
    private external fun setCoord(i: Int, v: Int)

    private external fun equal(other: Vec2i): Boolean
}