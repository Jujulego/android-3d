package net.capellari.julien.threed.math

// Classe
sealed class Dimension(val size: Int) {
    // Méthodes
    override fun equals(other: Any?)
            = (other is Dimension) && (size == other.size)

    override fun hashCode(): Int {
        return size
    }
}

// Valeurs
object D2: Dimension(2)
object D3: Dimension(3)
object D4: Dimension(4)