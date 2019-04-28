package net.capellari.julien.threed

import net.capellari.julien.threed.jni.JNIClass
import net.capellari.julien.threed.math.D2
import net.capellari.julien.threed.math.MatSize
import net.capellari.julien.threed.math.Matrix

class Mat2i: JNIClass, Matrix<Int,D2,D2> {
    // Companion
    companion object {
        // Méthodes
        @JvmStatic private external fun create(): Long
        @JvmStatic private external fun createA(factors: IntArray): Long
        @JvmStatic private external fun createM(mat: Mat2i): Long
    }

    // Propriétés
    val data: IntArray get() = getDataA()

    // Constructeurs
    internal constructor(handle: Long): super(handle)
    internal constructor(factors: IntArray): this(createA(factors))

    constructor(): this(create())
    constructor(mat: Mat2i): this(createM(mat))
    constructor(aa: Int, ab: Int,
                ba: Int, bb: Int): this(intArrayOf(aa, ba, ab, bb))

    // Opérateurs
    override fun get(c: Int, l: Int) = getFactor(c, l)
    override fun set(c: Int, l: Int, v: Int) = setFactor(c, l, v)

    override fun unaryPlus(): Mat2i {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unaryMinus(): Mat2i {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun plusAssign(mat: Matrix<Int, D2, D2>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun minusAssign(mat: Matrix<Int, D2, D2>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun timesAssign(k: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun divAssign(k: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun plus(mat: Matrix<Int, D2, D2>): Mat2i {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun minus(mat: Matrix<Int, D2, D2>): Mat2i {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun times(k: Int): Mat2i {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun div(k: Int): Mat2i {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // Méthodes
    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other is Mat2i) {
            return equal(other)
        }

        return false
    }

    override fun lig(l: Int): Vec2i {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun col(c: Int): Vec2i {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun size() = MatSize(D2, D2)

    // Méthodes natives
    private external fun getDataA(): IntArray
    private external fun getFactor(c: Int, l: Int): Int
    private external fun setFactor(c: Int, l: Int, v: Int)

    private external fun equal(mat: Mat2i): Boolean
}