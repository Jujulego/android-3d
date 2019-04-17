package net.capellari.julien.threed.math

import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

interface SquareMatrix<Deg: Degres>: Matrix<Deg,Deg> {
    // Companion
    companion object {
        // Méthodes
        fun<Mat: SquareMatrix<*>> identity(cls: KClass<Mat>): Mat {
            return cls.createInstance().apply {
                fill { c, l -> if (c == l) 1f else 0f }
            }
        }
    }

    // Opérateurs
    operator fun times(mat: SquareMatrix<Deg>): SquareMatrix<Deg> {
        val res = copy(); res *= mat
        return res
    }
    operator fun timesAssign(mat: SquareMatrix<Deg>) {
        for (l in 0 until size.lig) {
            val lig = newVector()
            for (c in 0 until size.col) {
                lig[c] = this.lig(l) * mat.col(c)
            }

            this.lig(l, lig)
        }
    }

    // Méthodes
    fun newVector(): Vector<Deg>
    override fun newMatrix(): SquareMatrix<Deg>
    override fun copy() = super.copy() as SquareMatrix<Deg>

    override fun newColVector() = newVector()
    override fun newLigVector() = newVector()
}