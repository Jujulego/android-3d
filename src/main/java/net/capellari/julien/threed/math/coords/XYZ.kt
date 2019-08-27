package net.capellari.julien.threed.math.coords

import net.capellari.julien.threed.annotations.math.Generate
import net.capellari.julien.threed.annotations.math.Generator
import net.capellari.julien.threed.annotations.math.Generators.*
import net.capellari.julien.threed.annotations.math.NumberType
import net.capellari.julien.threed.math.Coord
import net.capellari.julien.threed.math.D3

@Generate(
    Generator(VECTOR, NumberType.INT, 3),
    Generator(VECTOR, NumberType.FLOAT, 3),

    Generator(MATRIX, NumberType.INT, 3),
    Generator(MATRIX, NumberType.FLOAT, 3),

    Generator(VECTOR_ARRAY, NumberType.INT, 3),
    Generator(VECTOR_ARRAY, NumberType.FLOAT, 3)
)
interface XYZ<T: Number>: Coord<T, D3> {
    // Propriétés
    var x get() = get(0)
        set(v) = set(0, v)

    var y get() = get(1)
        set(v) = set(1, v)

    var z get() = get(2)
        set(v) = set(2, v)

    // Méthodes
    override fun size()= D3.size
}