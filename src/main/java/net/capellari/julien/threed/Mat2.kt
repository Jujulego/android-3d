package net.capellari.julien.threed

import net.capellari.julien.threed.math.SquareMatrix
import net.capellari.julien.threed.math.Two
import net.capellari.julien.threed.math.bases.FloatSquareMatrix

class Mat2() : FloatSquareMatrix<Two>(Two) {
    // Companion
    companion object {
        // Méthodes
        fun identity() = SquareMatrix.identity(Mat2::class)
    }

    // Constructeurs
    constructor(aa: Float, ab: Float,
                ba: Float, bb: Float): this() {

        this[0,0] = aa; this[1,0] = ab
        this[0,1] = ba; this[1,1] = bb
    }

    // Méthodes
    override fun newMatrix() = Mat2()
    override fun newVector() = Vec2()
}