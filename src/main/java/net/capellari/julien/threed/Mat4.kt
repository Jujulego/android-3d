package net.capellari.julien.threed

import android.opengl.Matrix
import net.capellari.julien.threed.math.Four
import net.capellari.julien.threed.math.SquareMatrix
import net.capellari.julien.threed.math.Vector
import net.capellari.julien.threed.math.bases.FloatSquareMatrix

class Mat4() : FloatSquareMatrix<Four>(Four) {
    // Companion
    companion object {
        // Méthodes
        fun identity() = SquareMatrix.identity(Mat4::class)

        /**
         * Computes an orthographic projection matrix.
         */
        fun ortho(left: Float, right: Float, bottom: Float, top: Float,
                  near: Float, far: Float) = Mat4().apply {
            Matrix.orthoM(data, 0, left, right, bottom, top, near, far)
        }

        /**
         * Defines a projection matrix in terms of six clip planes.
         */
        fun frustum(left: Float, right: Float, bottom: Float, top: Float,
                    near: Float, far: Float) = Mat4().apply {
            Matrix.frustumM(data, 0, left, right, bottom, top, near, far)
        }

        /**
         * Defines a projection matrix in terms of a field of view angle, an
         * aspect ratio, and z clip planes.
         */
        fun perspective(fovy: Float, aspect: Float, zNear: Float, zFar: Float) = Mat4().apply {
            Matrix.perspectiveM(data, 0, fovy, aspect, zNear, zFar)
        }

        /**
         * Defines a viewing transformation in terms of an eye point, a center of
         * view, and an up vector.
         */
        fun lookAt(eye: Vec3, center: Vec3, up: Vec3) = Mat4().apply {
            Matrix.setLookAtM(data, 0, eye.x, eye.y, eye.z, center.x, center.y, center.z, up.x, up.y, up.z)
        }

        /**
         * Defines a scaling matrix
         */
        fun scale(v: Vec3) = identity().scale(v)

        /**
         * Defines a translating matrix
         */
        fun translate(v: Vec3) = identity().translate(v)

        /**
         * Defines a rotation matrix of a degrees around the axis defined by (x, y, z)
         */
        fun rotate(a: Float, v: Vec3) = Mat4().apply {
            Matrix.rotateM(data, 0, a, v.x, v.y, v.z)
        }

        /**
         * Converts Euler angles to rotation matrix
         */
        fun rotateEuler(x: Float, y: Float, z: Float) = Mat4().apply {
            Matrix.setRotateEulerM(data, 0, x, y, z)
        }
    }

    // Constructeurs
    constructor(aa: Float, ab: Float, ac: Float, ad: Float,
                ba: Float, bb: Float, bc: Float, bd: Float,
                ca: Float, cb: Float, cc: Float, cd: Float,
                da: Float, db: Float, dc: Float, dd: Float): this() {

        this[0,0] = aa; this[1,0] = ab; this[2,0] = ac; this[3,0] = ad
        this[0,1] = ba; this[1,1] = bb; this[2,1] = bc; this[3,1] = bd
        this[0,2] = ca; this[1,2] = cb; this[2,2] = cc; this[3,2] = cd
        this[0,3] = da; this[1,3] = db; this[2,3] = dc; this[3,3] = dd
    }

    // Opérateurs
    override fun times(mat: SquareMatrix<Four>): SquareMatrix<Four> {
        if (mat is Mat4) {
            return Mat4().also {
                Matrix.multiplyMM(it.data, 0, data, 0, mat.data, 0)
            }
        }

        return super.times(mat)
    }
    override fun times(v: Vector<Four>): Vector<Four> {
        if (v is Vec4) {
            return Vec4().also {
                Matrix.multiplyMV(it.data, 0, data, 0, v.data, 0)
            }
        }

        return super.times(v)
    }
    override fun timesAssign(mat: SquareMatrix<Four>) {
        if (mat is Mat4) {
            val odata = FloatArray(size.size) { i -> data[i] }
            Matrix.multiplyMM(data, 0, odata, 0, mat.data, 0)
        }

        super.timesAssign(mat)
    }

    // Méthodes
    override fun newMatrix() = Mat4()
    override fun newVector() = Vec4()

    /**
     * Inverts the matrix
     */
    fun invert() = Mat4().also {
        Matrix.invertM(it.data, 0, data, 0)
    }

    /**
     * Scales matrix by x, y, and z.
     */
    fun scale(v: Vec3) = Mat4.also {
        Matrix.scaleM(data, 0, v.x, v.y, v.z)
    }

    /**
     * Translates matrix by x, y and z.
     */
    fun translate(v: Vec3) = Mat4.also {
        Matrix.translateM(data, 0, v.x, v.y, v.z)
    }

    /**
     * Rotates matrix by a around the axis defined by (x, y, z)
     */
    fun rotate(a: Float, v: Vec3) = Mat4.also {
        Matrix.rotateM(data, 0, a, v.x, v.y, v.z)
    }
}