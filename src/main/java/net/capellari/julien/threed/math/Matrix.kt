package net.capellari.julien.threed.math

interface Matrix<Col: Degres, Lig: Degres> {
    // Attributs
    val size: MatSize<Col,Lig>

    // Opérateurs
    operator fun get(c: Int, l: Int): Float
    operator fun set(c: Int, l: Int, v: Float)

    operator fun unaryPlus() = copy()
    operator fun unaryMinus() = newMatrix().also { it.fill { c, l -> -this[c,l] } }

    operator fun plus(mat: Matrix<Col,Lig>): Matrix<Col,Lig> {
        val res = copy(); res += mat
        return res
    }
    operator fun plusAssign(mat: Matrix<Col,Lig>) {
        for (c in 0 until size.col) {
            for (l in 0 until size.lig) {
                this[c,l] += mat[c,l]
            }
        }
    }

    operator fun minus(mat: Matrix<Col,Lig>): Matrix<Col,Lig> {
        val res = copy(); res -= mat
        return res
    }
    operator fun minusAssign(mat: Matrix<Col,Lig>) {
        for (c in 0 until size.col) {
            for (l in 0 until size.lig) {
                this[c,l] -= mat[c,l]
            }
        }
    }

    operator fun times(v: Vector<Col>): Vector<Lig> {
        val res = newLigVector()
        for (l in 0 until size.lig) {
            res[l] = lig(l) * v
        }

        return res
    }
    operator fun times(k: Float): Matrix<Col,Lig> {
        val mat = copy(); mat *= k
        return mat
    }
    operator fun timesAssign(k: Float) {
        for (c in 0 until size.col) {
            for (l in 0 until size.lig) {
                this[c,l] *= k
            }
        }
    }

    operator fun div(k: Float): Matrix<Col,Lig> {
        val mat = copy(); mat /= k
        return mat
    }
    operator fun divAssign(k: Float) {
        for (c in 0 until size.col) {
            for (l in 0 until size.lig) {
                this[c,l] /= k
            }
        }
    }

    // Méthodes
    fun newMatrix(): Matrix<Col,Lig>
    fun copy(): Matrix<Col,Lig> {
        val res = newMatrix()
        for (c in 0 until size.col) {
            for (l in 0 until size.lig) {
                res[c,l] = this[c,l]
            }
        }

        return res
    }

    fun newColVector(): Vector<Col>
    fun newLigVector(): Vector<Lig>

    fun col(c: Int): Vector<Lig> {
        val res = newLigVector()
        for (l in 0 until size.lig) {
            res[l] = this[c,l]
        }

        return res
    }
    fun col(c: Int, v: Vector<Lig>) {
        for (l in 0 until size.lig) {
            this[c,l] = v[l]
        }
    }

    fun lig(l: Int): Vector<Col> {
        val res = newColVector()
        for (c in 0 until size.col) {
            res[c] = this[c,l]
        }

        return res
    }
    fun lig(l: Int, v: Vector<Col>) {
        for (c in 0 until size.col) {
            this[c,l] = v[c]
        }
    }

    fun fill(cb: (c: Int, l: Int) -> Float) {
        for (c in 0 until size.col) {
            for (l in 0 until size.lig) {
                this[c,l] = cb(c, l)
            }
        }
    }
    fun fill(v: Float) = fill { _, _ -> v }
}