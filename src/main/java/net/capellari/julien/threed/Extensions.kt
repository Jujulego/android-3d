package net.capellari.julien.threed

import net.capellari.julien.threed.math.Dimension
import net.capellari.julien.threed.math.Vector

// Math
inline operator fun <reified T: Number,D: Dimension> T.times(v: Vector<T,D>): Vector<T,D> = v * this