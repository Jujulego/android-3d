package net.capellari.julien.threed

import android.util.Log
import net.capellari.julien.threed.jni.JNIClass
import net.capellari.julien.threed.math.*

class Mat2i: JNIClass, Matrix<Int,D2,D2> {
    // Companion
    companion object {
        // Méthodes
        @JvmStatic private external fun create(): Long
        @JvmStatic private external fun createA(factors: IntArray): Long
        @JvmStatic private external fun createM(mat: Mat2i): Long
    }

    // Propriétés
    override val size = MatSize(D2, D2)
    val data: IntArray get() = getDataA()

    // Constructeurs
    internal constructor(handle: Long): super(handle)
    internal constructor(factors: IntArray): this(createA(factors))

    constructor(): this(create())
    constructor(mat: Mat2i): this(createM(mat))
    constructor(gen: (Int) -> Int): this(IntArray(4, gen))
    constructor(aa: Int, ab: Int,
                ba: Int, bb: Int): this(intArrayOf(aa, ba, ab, bb))

    // Opérateurs
    override fun get(c: Int, l: Int) = getFactor(c, l)
    override fun set(c: Int, l: Int, v: Int) = setFactor(c, l, v)

    override fun unaryPlus()  = Mat2i(data)
    override fun unaryMinus() = data { Mat2i { i -> -it[i] }}

    override fun plusAssign(mat: Matrix<Int, D2, D2>) {
        if (mat is Mat2i) { // Prefer native call
            return plusA(mat)
        }

        for (l in 0 until size.lig) {
            for (c in 0 until size.col) {
                this[c,l] += mat[c,l]
            }
        }
    }
    override fun minusAssign(mat: Matrix<Int, D2, D2>) {
        if (mat is Mat2i) { // Prefer native call
            return minusA(mat)
        }

        for (l in 0 until size.lig) {
            for (c in 0 until size.col) {
                this[c,l] -= mat[c,l]
            }
        }
    }

    override fun timesAssign(k: Int) = timesA(k)
    override fun divAssign(k: Int)   = divA(k)

    override fun plus(mat: Matrix<Int, D2, D2>)  = Mat2i(this).also { it += mat }
    override fun minus(mat: Matrix<Int, D2, D2>) = Mat2i(this).also { it -= mat }

    override fun times(k: Int) = data { Mat2i { i -> k * it[i] }}
    override fun div(k: Int)   = data { Mat2i { i -> it[i] / k }}

    override fun times(pt: Point<Int, D2>): Point2i {
        return Point2i { lig(it) * pt }
    }

    override fun times(v: Vector<Int, D2>): Vec2i {
        return Vec2i { lig(it) * v }
    }

    // Méthodes
    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other is Mat2i) {
            return equal(other)
        }

        return false
    }
    override fun hashCode(): Int {
        return data.hashCode()
    }

    override fun lig(l: Int) = Vec2i(this[0,l], this[1,l])
    override fun col(c: Int) = Vec2i(this[c,0], this[c,1])

    inline fun<reified T> data(f: (IntArray) -> T): T = data.let(f)

    // Méthodes natives
    private external fun getDataA(): IntArray
    private external fun getFactor(c: Int, l: Int): Int
    private external fun setFactor(c: Int, l: Int, v: Int)

    private external fun equal(mat: Mat2i): Boolean

    private external fun plusA(mat: Mat2i)
    private external fun minusA(mat: Mat2i)

    private external fun timesA(k: Int)
    private external fun divA(k: Int)
}