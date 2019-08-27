package net.capellari.julien.threed.math.coords

import net.capellari.julien.threed.annotations.math.Generate
import net.capellari.julien.threed.annotations.math.Generator
import net.capellari.julien.threed.annotations.math.Generators.*
import net.capellari.julien.threed.annotations.math.NumberType
import net.capellari.julien.threed.math.Coord
import net.capellari.julien.threed.math.D4

@Generate(
    Generator(VECTOR, NumberType.INT, 4),
    Generator(VECTOR, NumberType.FLOAT, 4),

    Generator(MATRIX, NumberType.INT, 4),
    Generator(MATRIX, NumberType.FLOAT, 4),

    Generator(VECTOR_ARRAY, NumberType.INT, 4),
    Generator(VECTOR_ARRAY, NumberType.FLOAT, 4)
)
interface XYZA<T: Number>: Coord<T, D4> {
    // Propriétés
    var x get() = get(0)
        set(v) = set(0, v)

    var y get() = get(1)
        set(v) = set(1, v)

    var z get() = get(2)
        set(v) = set(2, v)

    var a get() = get(3)
        set(v) = set(3, v)

    // Méthodes
    override fun size()= D4.size
}