package net.capellari.julien.threed.math.bases

import net.capellari.julien.threed.math.Coords
import net.capellari.julien.threed.math.Degres

abstract class FloatCoords<Deg: Degres>(d: Deg) :
    Coords<Deg> {
    // Attributs
    final override val degres = d.value
    protected val data = FloatArray(degres) { 0f }

    // Opérateurs
    override fun get(i: Int) = data[i]
    override fun set(i: Int, v: Float) = data.set(i, v)

    override fun iterator() = data.iterator()

    // Méthodes
    override fun equals(other: Any?): Boolean {
        if (other is FloatCoords<*> && degres == other.degres) {
            for (i in 0 until degres) {
                if (this[i] != other[i]) return false
            }

            return true
        }

        return false
    }

    override fun hashCode() = data.contentHashCode()
}