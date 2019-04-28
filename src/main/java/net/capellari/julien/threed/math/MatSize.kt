package net.capellari.julien.threed.math

// Classes
class MatSize<L: Dimension, C: Dimension>(c: L, l: C) {
    // Attributs
    val lig = l.size
    val col = c.size

    val size = c.size * l.size

    // Méthodes
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is MatSize<*, *>) {
            return other.col == col && other.lig == lig
        }

        return false
    }

    override fun hashCode(): Int {
        var result = col
        result = 31 * result + lig
        return result
    }
}