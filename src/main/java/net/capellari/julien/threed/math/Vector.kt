package net.capellari.julien.threed.math

interface Vector<Deg : Degres>: Coords<Deg> {
    // Opérateurs
    operator fun unaryPlus():  Vector<Deg>
    operator fun unaryMinus(): Vector<Deg>

    operator fun plus(pt: Point<Deg>): Point<Deg>
    operator fun plus(v: Vector<Deg>): Vector<Deg>
    operator fun plusAssign(v: Vector<Deg>) {
        for (i in 0 until degres) this[i] += v[i]
    }

    operator fun minus(pt: Point<Deg>): Point<Deg>
    operator fun minus(v: Vector<Deg>): Vector<Deg>
    operator fun minusAssign(v: Vector<Deg>) {
        for (i in 0 until degres) this[i] -= v[i]
    }

    operator fun times(k: Float): Vector<Deg>
    operator fun timesAssign(k: Float) {
        for (i in 0 until degres) this[i] *= k
    }

    operator fun div(k: Float): Vector<Deg>
    operator fun divAssign(k: Float) {
        for (i in 0 until degres) this[i] /= k
    }

    // Infixes
    infix fun scalar(v: Vector<Deg>): Float {
        var r = 0f
        for (i in 0 until degres) {
            r += this[i] * v[i]
        }

        return r
    }
}