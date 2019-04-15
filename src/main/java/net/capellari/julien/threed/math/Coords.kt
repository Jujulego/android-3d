package net.capellari.julien.threed.math

interface Coords<Deg: Degres>: Iterable<Float> {
    // Propriétés
    val degres: Int

    // Opérateurs
    operator fun get(i: Int): Float
    operator fun set(i: Int, v: Float)

    override operator fun iterator(): Iterator<Float>

    // Méthodes
    override fun equals(other: Any?): Boolean
}