package net.capellari.julien.threed

import net.capellari.julien.threed.math.Two
import net.capellari.julien.threed.math.bases.FloatSquareMatrix

class Mat2() : FloatSquareMatrix<Two>(Two) {
    // Méthodes
    override fun newMatrix() = Mat2()
    override fun newVector() = Vec2()
}