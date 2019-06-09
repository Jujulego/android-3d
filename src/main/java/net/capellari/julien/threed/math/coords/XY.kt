package net.capellari.julien.threed.math.coords

import net.capellari.julien.threed.annotations.math.Generate
import net.capellari.julien.threed.annotations.math.Generator
import net.capellari.julien.threed.annotations.math.Generators.POINT
import net.capellari.julien.threed.annotations.math.NumberType
import net.capellari.julien.threed.math.Coord
import net.capellari.julien.threed.math.D2

@Generate(
    Generator(POINT, NumberType.INT, 2),
    Generator(POINT, NumberType.FLOAT, 2)
)
interface XY<T: Number>: Coord<T, D2> {
    // Propriétés
    var x get() = get(0)
        set(v) = set(0, v)

    var y get() = get(1)
        set(v) = set(1, v)

    // Méthodes
    override fun size()= D2.size
}