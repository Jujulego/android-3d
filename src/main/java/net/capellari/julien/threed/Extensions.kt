package net.capellari.julien.threed

import net.capellari.julien.threed.math.Dimension
import net.capellari.julien.threed.math.Matrix
import net.capellari.julien.threed.math.Vector

// Math
inline operator fun <reified T: Number, reified D: Dimension>
        T.times(v: Vector<T,D>): Vector<T,D> = v * this

inline operator fun <reified T: Number, reified L: Dimension, reified C: Dimension>
        T.times(m: Matrix<T,L,C>): Matrix<T,L,C> = m * this