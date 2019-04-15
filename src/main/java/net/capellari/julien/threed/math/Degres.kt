package net.capellari.julien.threed.math

// Classe
sealed class Degres(val value: Int) {
    // Méthodes
    override fun equals(other: Any?)
            = (other is Degres) && (value == other.value)

    override fun hashCode(): Int {
        return value
    }
}

// Valeurs
object Two: Degres(2)