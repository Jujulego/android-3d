package net.capellari.julien.threed.math

interface Matrix<Col: Degres, Lig: Degres> {
    // Attributs
    val size: MatSize<Col,Lig>

    // Opérateurs
    operator fun get(c: Int, l: Int): Float
    operator fun set(c: Int, l: Int, v: Float)

    operator fun plus(mat: Matrix<Col,Lig>): Matrix<Col,Lig> {
        val res = copy(this); res += mat
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
        val res = copy(this); res -= mat
        return res
    }
    operator fun minusAssign(mat: Matrix<Col,Lig>) {
        for (c in 0 until size.col) {
            for (l in 0 until size.lig) {
                this[c,l] -= mat[c,l]
            }
        }
    }

    operator fun times(k: Float): Matrix<Col,Lig> {
        val mat = copy(this); mat *= k
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
        val mat = copy(this); mat /= k
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
    fun copy(mat: Matrix<Col,Lig>): Matrix<Col,Lig> {
        val res = newMatrix()
        for (c in 0 until size.col) {
            for (l in 0 until size.lig) {
                res[c,l] = mat[c,l]
            }
        }

        return res
    }

    fun newColVector(): Vector<Col>
    fun newLigVector(): Vector<Lig>

    fun col(c: Int): Vector<Col> {
        val res = newColVector()
        for (l in 0 until size.lig) {
            res[l] = this[c,l]
        }

        return res
    }
    fun col(c: Int, v: Vector<Col>) {
        for (l in 0 until size.lig) {
            this[c,l] = v[l]
        }
    }

    fun lig(l: Int): Vector<Lig> {
        val res = newLigVector()
        for (c in 0 until size.lig) {
            res[c] = this[c,l]
        }

        return res
    }
    fun lig(l: Int, v: Vector<Lig>) {
        for (c in 0 until size.col) {
            this[c,l] = v[l]
        }
    }
}