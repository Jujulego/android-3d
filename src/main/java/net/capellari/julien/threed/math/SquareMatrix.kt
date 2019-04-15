package net.capellari.julien.threed.math

interface SquareMatrix<Deg: Degres>: Matrix<Deg,Deg> {
    // Opérateurs
    operator fun times(mat: SquareMatrix<Deg>): SquareMatrix<Deg> {
        val res = newMatrix(); res *= mat
        return res
    }
    operator fun timesAssign(mat: SquareMatrix<Deg>) {
        for (l in 0 until size.lig) {
            val lig = newVector()
            for (c in 0 until size.col) {
                lig[c] = this.lig(l) * mat.col(c)
            }

            this.lig(l, lig)
        }
    }

    // Méthodes
    fun newVector(): Vector<Deg>
    override fun newMatrix(): SquareMatrix<Deg>

    override fun newColVector() = newVector()
    override fun newLigVector() = newVector()
}