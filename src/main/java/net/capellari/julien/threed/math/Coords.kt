package net.capellari.julien.threed.math

interface Coords<Deg: Degres>: Iterable<Float> {
    // Propriétés
    val degres: Int

    // Opérateurs
    operator fun get(i: Int): Float
    operator fun set(i: Int, v: Float)

    // Méthodes
    override fun equals(other: Any?): Boolean

    fun newCoords(): Coords<Deg>
    fun copy(): Coords<Deg> {
        val res = newCoords()
        for (i in 0 until degres) {
            res[i] = this[i]
        }

        return res
    }
}