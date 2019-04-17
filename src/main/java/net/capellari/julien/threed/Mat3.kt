package net.capellari.julien.threed

import net.capellari.julien.threed.math.SquareMatrix
import net.capellari.julien.threed.math.Three
import net.capellari.julien.threed.math.bases.FloatSquareMatrix

class Mat3() : FloatSquareMatrix<Three>(Three) {
    // Companion
    companion object {
        // Méthodes
        fun identity() = SquareMatrix.identity(Mat3::class)
    }

    // Constructeurs
    constructor(aa: Float, ab: Float, ac: Float,
                ba: Float, bb: Float, bc: Float,
                ca: Float, cb: Float, cc: Float): this() {

        this[0,0] = aa; this[1,0] = ab; this[2,0] = ac
        this[0,1] = ba; this[1,1] = bb; this[2,1] = bc
        this[0,2] = ca; this[1,2] = cb; this[2,2] = cc
    }

    // Méthodes
    override fun newMatrix() = Mat3()
    override fun newVector() = Vec3()
}