package net.capellari.julien.threed.math.bases

import net.capellari.julien.threed.math.Coords
import net.capellari.julien.threed.math.Degres

abstract class FloatCoords<Deg: Degres>(d: Deg): Coords<Deg> {
    // Attributs
    final override val degres = d.value
    var data = FloatArray(degres) { 0f }
        private set

    // Opérateurs
    override fun get(i: Int) = data[i]
    override fun set(i: Int, v: Float) = data.set(i, v)

    override fun iterator() = data.iterator()

    // Méthodes
    override fun equals(other: Any?): Boolean {
        if (other is FloatCoords<*> && degres == other.degres) {
            return other.data.contentEquals(data)
        }

        return false
    }

    override fun hashCode() = data.contentHashCode()
}