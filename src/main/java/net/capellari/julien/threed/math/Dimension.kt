package net.capellari.julien.threed.math

// Classe
sealed class Dimension(val value: Int) {
    // Méthodes
    override fun equals(other: Any?)
            = (other is Dimension) && (value == other.value)

    override fun hashCode(): Int {
        return value
    }
}

// Valeurs
object D2: Dimension(2)
object D3: Dimension(3)
object D4: Dimension(4)