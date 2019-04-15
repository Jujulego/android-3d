package net.capellari.julien.threed

import net.capellari.julien.threed.math.Three
import net.capellari.julien.threed.math.bases.FloatSquareMatrix

class Mat3() : FloatSquareMatrix<Three>(Three) {
    // Méthodes
    override fun newMatrix() = Mat3()
    override fun newVector() = Vec3()
}