package net.capellari.julien.threed

import net.capellari.julien.threed.math.Degres
import net.capellari.julien.threed.math.Matrix
import net.capellari.julien.threed.math.Vector

// Opérations
operator fun<Deg: Degres> Float.times(v: Vector<Deg>) = (v * this)
operator fun<Col: Degres, Lig: Degres> Float.times(mat: Matrix<Col,Lig>) = (mat * this)