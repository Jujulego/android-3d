package net.capellari.julien.threed.math

interface Point<Deg : Degres>: Coords<Deg> {
    // Opérateurs
    operator fun unaryPlus():  Point<Deg> = copy()
    operator fun unaryMinus(): Point<Deg> {
        val res = newPoint()
        for (i in 0 until degres) {
            res[i] = -this[i]
        }

        return res
    }

    operator fun plus(v: Vector<Deg>): Point<Deg> {
        val res = copy(); res += v
        return res
    }
    operator fun plusAssign(v: Vector<Deg>) {
        for (i in 0 until degres) this[i] += v[i]
    }

    operator fun minus(pt: Point<Deg>): Vector<Deg> {
        val res = newVector()
        for (i in 0 until degres) {
            res[i] = this[i] - pt[i]
        }

        return res
    }
    operator fun minus(v: Vector<Deg>): Point<Deg> {
        val res = copy(); res -= v
        return res
    }
    operator fun minusAssign(v: Vector<Deg>) {
        for (i in 0 until degres) this[i] -= v[i]
    }

    // Méthodes
    fun newPoint(): Point<Deg>
    fun newVector(): Vector<Deg>

    override fun newCoords() = newPoint()
    override fun copy() = super.copy() as Point<Deg>
}