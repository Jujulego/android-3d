package net.capellari.julien.threed.math.coords

import net.capellari.julien.threed.math.Coord
import net.capellari.julien.threed.math.D2

interface XY<T: Number>: Coord<T, D2> {
    // Propriétés
    var x get() = get(0)
        set(v) = set(0, v)

    var y get() = get(1)
        set(v) = set(1, v)

    // Méthodes
    override fun size()= D2.size
}