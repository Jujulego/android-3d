package net.capellari.julien.threed

import net.capellari.julien.threed.jni.JNIClass
import net.capellari.julien.threed.math.*
import net.capellari.julien.threed.math.coords.XY

class Vec2f: JNIClass, XY<Float>, Vector<Float,D2> {
    // Companion
    companion object {
        // Méthodes
        @JvmStatic private external fun create(x: Float, y: Float): Long
        @JvmStatic private external fun createA(factors: FloatArray): Long
    }

    // Propriétés
    val data get() = getDataA()

    // Constructeurs
    internal constructor(handle: Long): super(handle)

    constructor(x: Float = 0f, y: Float = 0f): this(create(x, y))

    constructor(factors: FloatArray): this(createA(factors))
    constructor(gen: (Int) -> Float): this(FloatArray(2, gen))

    // Opérateurs
    override operator fun get(i: Int)         = getCoord(i)
    override operator fun set(i: Int, v: Float) = setCoord(i, v)

    override fun unaryPlus()  = Vec2f(+x, +y)
    override fun unaryMinus() = Vec2f(-x, -y)

    override fun plusAssign(v: Vector<Float, D2>) {
        x += v[0]
        y += v[1]
    }
    override fun minusAssign(v: Vector<Float, D2>) {
        x -= v[0]
        y -= v[1]
    }
    override fun timesAssign(k: Float) {
        x *= k
        y *= k
    }
    override fun divAssign(k: Float) {
        x /= k
        y /= k
    }

    override fun plus(v: Vector<Float, D2>)  = Vec2f(x + v[0], y + v[1])
    override fun minus(v: Vector<Float, D2>) = Vec2f(x - v[0], y - v[1])
    override fun times(k: Float)             = Vec2f(x * k, y * k)
    override fun div(k: Float)               = Vec2f(x / k, y / k)

    override fun times(c: Coord<Float, D2>) = (x * c[0]) + (y * c[1])

    // Méthodes
    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other is Vec2f) {
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
    private external fun getDataA(): FloatArray
    private external fun getCoord(i: Int): Float
    private external fun setCoord(i: Int, v: Float)

    private external fun equal(other: Vec2f): Boolean
}