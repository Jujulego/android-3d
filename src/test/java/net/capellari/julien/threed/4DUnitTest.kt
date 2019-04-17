package net.capellari.julien.threed

import org.junit.Test
import org.junit.Assert.*

class `4DUnitTest` {
    // Tests
    @Test fun get_mat() {
        val mat = Mat4(
             1f,  2f,  3f,  4f,
             5f,  6f,  7f,  8f,
             9f, 10f, 11f, 12f,
            13f, 14f, 15f, 16f
        )

        assertEquals(1f,  mat[0,0])
        assertEquals(2f,  mat[1,0])
        assertEquals(3f,  mat[2,0])
        assertEquals(4f,  mat[3,0])
        assertEquals(5f,  mat[0,1])
        assertEquals(6f,  mat[1,1])
        assertEquals(7f,  mat[2,1])
        assertEquals(8f,  mat[3,1])
        assertEquals(9f,  mat[0,2])
        assertEquals(10f, mat[1,2])
        assertEquals(11f, mat[2,2])
        assertEquals(12f, mat[3,2])
        assertEquals(13f, mat[0,3])
        assertEquals(14f, mat[1,3])
        assertEquals(15f, mat[2,3])
        assertEquals(16f, mat[3,3])

        assertEquals(Vec4( 1f,  2f,  3f,  4f), mat.lig(0))
        assertEquals(Vec4( 5f,  6f,  7f,  8f), mat.lig(1))
        assertEquals(Vec4( 9f, 10f, 11f, 12f), mat.lig(2))
        assertEquals(Vec4(13f, 14f, 15f, 16f), mat.lig(3))

        assertEquals(Vec4( 1f,  5f,  9f, 13f), mat.col(0))
        assertEquals(Vec4( 2f,  6f, 10f, 14f), mat.col(1))
        assertEquals(Vec4( 3f,  7f, 11f, 15f), mat.col(2))
        assertEquals(Vec4( 4f,  8f, 12f, 16f), mat.col(3))
    }
    @Test fun set_mat() {
        val mat = Mat4()

        mat.lig(0, Vec4(1f, 2f, 3f, 4f))
        assertEquals(Vec4(1f, 2f, 3f, 4f), mat.lig(0))

        mat.col(0, Vec4(1f, 2f, 3f, 4f))
        assertEquals(Vec4(1f, 2f, 3f, 4f), mat.col(0))
    }

    @Test fun unary_point() {
        val pt = Point4(5f, 3f, 2f, 7f)

        assertEquals(Point4( 5f,  3f,  2f,  7f), +pt)
        assertEquals(Point4(-5f, -3f, -2f, -7f), -pt)
    }
    @Test fun unary_vec() {
        val pt = Vec4(5f, 3f, 2f, 7f)

        assertEquals(Vec4(5f, 3f, 2f, 7f), +pt)
        assertEquals(Vec4(-5f, -3f, -2f, -7f), -pt)
    }
    @Test fun unary_mat() {
        val mat = Mat4(
             1f,  2f,  3f,  4f,
             5f,  6f,  7f,  8f,
             9f, 10f, 11f, 12f,
            13f, 14f, 15f, 16f
        )

        assertEquals(Mat4( 1f,  2f,  3f,  4f,  5f,  6f,  7f,  8f,  9f,  10f,  11f,  12f,  13f,  14f,  15f,  16f), +mat)
        assertEquals(Mat4(-1f, -2f, -3f, -4f, -5f, -6f, -7f, -8f, -9f, -10f, -11f, -12f, -13f, -14f, -15f, -16f), -mat)
    }

    @Test fun plus_point() {
        val pt = Point4()
        val v  = Vec4(5f, 3f, 2f, 7f)

        assertEquals(Point4(5f, 3f, 2f, 7f), pt + v)
        assertEquals(Point4(5f, 3f, 2f, 7f), v + pt)

        pt += v
        assertEquals(Point4(5f, 3f, 2f, 7f), pt)
    }
    @Test fun plus_vec() {
        val v = Vec4(5f, 3f, 2f, 7f)

        assertEquals(Vec4(10f, 6f, 4f, 14f), v + v)

        v += v
        assertEquals(Vec4(10f, 6f, 4f, 14f), v)
    }
    @Test fun plus_mat() {
        val mat = Mat4(
             1f,  2f,  3f,  4f,
             5f,  6f,  7f,  8f,
             9f, 10f, 11f, 12f,
            13f, 14f, 15f, 16f
        )

        assertEquals(Mat4(2f, 4f, 6f, 8f, 10f, 12f, 14f, 16f, 18f, 20f, 22f, 24f, 26f, 28f, 30f, 32f), mat + mat)

        mat += mat
        assertEquals(Mat4(2f, 4f, 6f, 8f, 10f, 12f, 14f, 16f, 18f, 20f, 22f, 24f, 26f, 28f, 30f, 32f), mat)
    }

    @Test fun minus_point() {
        val pt = Point4()
        val v  = Vec4(5f, 3f, 2f, 7f)

        assertEquals(Point4(-5f, -3f, -2f, -7f), pt - v)
        assertEquals(Point4( 5f,  3f,  2f,  7f), v - pt)
        assertEquals(Vec4(2f, 4f, 1f, 5f), Point4(8f, 5f, 9f, 18f) - Point4(6f, 1f, 8f, 13f))

        pt -= v
        assertEquals(Point4(-5f, -3f, -2f, -7f), pt)
    }
    @Test fun minus_vec() {
        val v = Vec4(5f, 3f, 2f, 7f)

        assertEquals(Vec4(), v - v)

        v -= v
        assertEquals(Vec4(), v)
    }
    @Test fun minus_mat() {
        val mat = Mat4(
             1f,  2f,  3f,  4f,
             5f,  6f,  7f,  8f,
             9f, 10f, 11f, 12f,
            13f, 14f, 15f, 16f
        )

        assertEquals(Mat4(), mat - mat)

        mat -= mat
        assertEquals(Mat4(), mat)
    }

    @Test fun times_vec() {
        val v = Vec4(5f, 3f, 2f, 7f)

        assertEquals(Vec4(10f, 6f, 4f, 14f), v * 2f)
        assertEquals(Vec4(10f, 6f, 4f, 14f), 2f * v)

        v *= 2f
        assertEquals(Vec4(10f, 6f, 4f, 14f), v)
    }
    @Test fun times_mat() {
        val mat = Mat4(
            1f,  2f,  3f,  4f,
            5f,  6f,  7f,  8f,
            9f, 10f, 11f, 12f,
            13f, 14f, 15f, 16f
        )

        assertEquals(Mat4(2f, 4f, 6f, 8f, 10f, 12f, 14f, 16f, 18f, 20f, 22f, 24f, 26f, 28f, 30f, 32f), mat * 2f)

        mat *= 2f
        assertEquals(Mat4(2f, 4f, 6f, 8f, 10f, 12f, 14f, 16f, 18f, 20f, 22f, 24f, 26f, 28f, 30f, 32f), mat)
    }

    @Test fun div_vec() {
        val v = Vec4(6f, 4f, 2f, 8f)

        assertEquals(Vec4(3f, 2f, 1f, 4f), v / 2f)

        v /= 2f
        assertEquals(Vec4(3f, 2f, 1f, 4f), v)
    }
    @Test fun div_mat() {
        val mat = Mat4(
             1f,  2f,  3f,  4f,
             5f,  6f,  7f,  8f,
             9f, 10f, 11f, 12f,
            13f, 14f, 15f, 16f
        )

        assertEquals(Mat4(.5f, 1f, 1.5f, 2f, 2.5f, 3f, 3.5f, 4f, 4.5f, 5f, 5.5f, 6f, 6.5f, 7f, 7.5f, 8f), mat / 2f)

        mat /= 2f
        assertEquals(Mat4(.5f, 1f, 1.5f, 2f, 2.5f, 3f, 3.5f, 4f, 4.5f, 5f, 5.5f, 6f, 6.5f, 7f, 7.5f, 8f), mat)
    }

    @Test fun norm() {
        assertEquals(1f, Vec4(0f, 1f, 0f, 0f).norm)
        assertEquals(5f, Vec4(5f, 0f, 0f, 0f).norm)
    }
    @Test fun scalar() {
        val v1 = Vec4(1f, 0f, 0f, 0f)
        val v2 = Vec4(0f, 1f, 0f, 0f)

        assertEquals(0f, v1 * v2)
        assertEquals(1f, v1 * v1)
    }
}