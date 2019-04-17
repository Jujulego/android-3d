package net.capellari.julien.threed.math

import kotlin.math.sqrt

interface Vector<Deg : Degres>: Coords<Deg> {
    // Propriétés
    val norm: Float get() {
        var n = 0f
        for (v in this) n += v*v

        return sqrt(n)
    }

    // Opérateurs
    operator fun unaryPlus():  Vector<Deg> = copy()
    operator fun unaryMinus(): Vector<Deg> {
        val res = newVector()
        for (i in 0 until degres) {
            res[i] = -this[i]
        }

        return res
    }

    operator fun plus(pt: Point<Deg>): Point<Deg> = pt.plus(this)
    operator fun plus(v: Vector<Deg>): Vector<Deg> {
        val res = copy(); res += v
        return res
    }
    operator fun plusAssign(v: Vector<Deg>) {
        for (i in 0 until degres) this[i] += v[i]
    }

    operator fun minus(pt: Point<Deg>): Point<Deg> = (-pt).minus(-this)
    operator fun minus(v: Vector<Deg>): Vector<Deg> {
        val res = copy(); res -= v
        return res
    }
    operator fun minusAssign(v: Vector<Deg>) {
        for (i in 0 until degres) this[i] -= v[i]
    }

    operator fun times(v: Vector<Deg>): Float {
        var r = 0f
        for (i in 0 until degres) {
            r += this[i] * v[i]
        }

        return r
    }
    operator fun times(k: Float): Vector<Deg> {
        val res = copy(); res *= k
        return res
    }
    operator fun timesAssign(k: Float) {
        for (i in 0 until degres) this[i] *= k
    }

    operator fun div(k: Float): Vector<Deg> {
        val res = copy(); res /= k
        return res
    }
    operator fun divAssign(k: Float) {
        for (i in 0 until degres) this[i] /= k
    }

    // Méthodes
    fun newPoint(): Point<Deg>
    fun newVector(): Vector<Deg>

    override fun newCoords() = newVector()
    override fun copy() = super.copy() as Vector<Deg>
}