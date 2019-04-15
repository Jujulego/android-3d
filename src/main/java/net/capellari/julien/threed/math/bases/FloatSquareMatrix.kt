package net.capellari.julien.threed.math.bases

import net.capellari.julien.threed.math.Degres
import net.capellari.julien.threed.math.MatSize
import net.capellari.julien.threed.math.SquareMatrix

abstract class FloatSquareMatrix<Deg: Degres>(d: Deg): SquareMatrix<Deg> {
    // Attributs
    final override val size = MatSize(d, d)
    protected val data = FloatArray(size.size) { 0f }

    // Opérateurs
    override fun get(c: Int, l: Int) = data[toIndex(c, l)]
    override fun set(c: Int, l: Int, v: Float) = data.set(toIndex(c, l), v)

    // Méthodes
    protected fun toIndex(c: Int, l: Int) = c + l * 2
}