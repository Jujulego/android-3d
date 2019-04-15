package net.capellari.julien.threed.math

interface Point<Deg : Degres>: Coords<Deg> {
    // Opérateurs
    operator fun unaryPlus():  Point<Deg>
    operator fun unaryMinus(): Point<Deg>

    operator fun plus(v: Vector<Deg>): Point<Deg>
    operator fun plusAssign(v: Vector<Deg>) {
        for (i in 0 until degres) this[i] += v[i]
    }

    operator fun minus(pt: Point<Deg>): Vector<Deg>
    operator fun minus(v: Vector<Deg>): Point<Deg>
    operator fun minusAssign(v: Vector<Deg>) {
        for (i in 0 until degres) this[i] -= v[i]
    }
}