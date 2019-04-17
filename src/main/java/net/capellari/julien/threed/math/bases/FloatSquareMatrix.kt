package net.capellari.julien.threed.math.bases

import net.capellari.julien.threed.math.Degres
import net.capellari.julien.threed.math.MatSize
import net.capellari.julien.threed.math.Matrix
import net.capellari.julien.threed.math.SquareMatrix

abstract class FloatSquareMatrix<Deg: Degres>(d: Deg): SquareMatrix<Deg> {
    // Attributs
    final override val size = MatSize(d, d)
    var data = FloatArray(size.size) { 0f }
        private set

    // Opérateurs
    final override fun get(c: Int, l: Int) = data[toIndex(c, l)]
    final override fun set(c: Int, l: Int, v: Float) = data.set(toIndex(c, l), v)

    // Méthodes
    override fun hashCode() = data.contentHashCode()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is FloatSquareMatrix<*> && other.size == other.size) {
            return other.data.contentEquals(data)
        }

        return false
    }

    protected fun toIndex(c: Int, l: Int) = l + c * size.lig

    abstract override fun newMatrix(): FloatSquareMatrix<Deg>
    final override fun copy(): FloatSquareMatrix<Deg> {
        return newMatrix().also { it.data = data.copyOf(size.size) }
    }
}