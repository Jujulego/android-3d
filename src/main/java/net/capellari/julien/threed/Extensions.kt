package net.capellari.julien.threed

import net.capellari.julien.threed.annotations.GPUType
import net.capellari.julien.threed.gpu.Type
import net.capellari.julien.threed.math.Dimension
import net.capellari.julien.threed.math.Matrix
import net.capellari.julien.threed.math.Vector
import net.capellari.julien.threed.math.VectorArray
import kotlin.reflect.full.findAnnotation

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
inline fun <reified T> gpuType(): Type
        = when (T::class) {
    Int::class -> Type.INT
    Float::class -> Type.FLOAT

    else -> {
        val annotation = T::class.findAnnotation<GPUType>()
        annotation?.type ?: throw RuntimeException("Unsupported type : ${T::class.simpleName}")
    }
}

inline fun <reified T> gpuSize(): Int
        = when (T::class) {
    Int::class, Float::class -> 1

    else -> {
        val annotation = T::class.findAnnotation<GPUType>()
        annotation?.size ?: throw RuntimeException("Unsupported type : ${T::class.simpleName}")
    }
}