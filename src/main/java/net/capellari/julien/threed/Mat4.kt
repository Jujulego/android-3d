package net.capellari.julien.threed

import net.capellari.julien.threed.math.Four
import net.capellari.julien.threed.math.bases.FloatSquareMatrix

class Mat4() : FloatSquareMatrix<Four>(Four) {
    // Méthodes
    override fun newMatrix() = Mat4()
    override fun newVector() = Vec4()
}