package net.capellari.julien.threed

import net.capellari.julien.threed.gpu.Program
import net.capellari.julien.threed.gpu.Uniformable
import net.capellari.julien.threed.math.Dimension
import net.capellari.julien.threed.math.Matrix
import net.capellari.julien.threed.math.Vector
import net.capellari.julien.threed.math.VectorArray

// Array
inline fun <reified V: Vector<*,*>> VectorArray<V>.forEach(cb: (V) -> Unit) {
    for (i in 0 until size) {
        cb(get(i))
    }
}

// Math
inline operator fun <reified T: Number, reified D: Dimension>
        T.times(v: Vector<T,D>): Vector<T,D> = v * this

inline operator fun <reified T: Number, reified L: Dimension, reified C: Dimension>
        T.times(m: Matrix<T,L,C>): Matrix<T,L,C> = m * this

inline fun <reified T: Number, reified L: Dimension, reified C: Dimension, reified R> Matrix<T,L,C>.factors(cb: (l: Int, c: Int, v: T) -> R): Array<R> {
    var res = arrayOf<R>()

    for (l in 0 until size.lig) {
        for (c in 0 until size.col) {
            res += cb(l, c, this[l,c])
        }
    }

    return res
}

// GPU
fun<T: Uniformable> Program.addUniform(name: String, default: T) = Uniform(addUniform(name), default)