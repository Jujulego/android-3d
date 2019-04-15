package net.capellari.julien.threed

import net.capellari.julien.threed.math.Degres
import net.capellari.julien.threed.math.Vector

// Opérations 2d
operator fun<Deg: Degres> Float.times(v: Vector<Deg>) = (v * this)