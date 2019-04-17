package net.capellari.julien.threed

import org.junit.Test
import org.junit.Assert.*

class `3DUnitTest` {
    // Tests
    @Test fun get_mat() {
        val mat = Mat3(
            1f, 2f, 3f,
            4f, 5f, 6f,
            7f, 8f, 9f
        )

        assertEquals(1f, mat[0,0])
        assertEquals(2f, mat[1,0])
        assertEquals(3f, mat[2,0])
        assertEquals(4f, mat[0,1])
        assertEquals(5f, mat[1,1])
        assertEquals(6f, mat[2,1])
        assertEquals(7f, mat[0,2])
        assertEquals(8f, mat[1,2])
        assertEquals(9f, mat[2,2])

        assertEquals(Vec3(1f, 2f, 3f), mat.lig(0))
        assertEquals(Vec3(4f, 5f, 6f), mat.lig(1))
        assertEquals(Vec3(7f, 8f, 9f), mat.lig(2))

        assertEquals(Vec3(1f, 4f, 7f), mat.col(0))
        assertEquals(Vec3(2f, 5f, 8f), mat.col(1))
        assertEquals(Vec3(3f, 6f, 9f), mat.col(2))
    }
    @Test fun set_mat() {
        val mat = Mat3()

        mat.lig(0, Vec3(1f, 2f, 3f))
        assertEquals(Vec3(1f, 2f, 3f), mat.lig(0))

        mat.col(0, Vec3(1f, 2f, 3f))
        assertEquals(Vec3(1f, 2f, 3f), mat.col(0))
    }

    @Test fun unary_point() {
        val pt = Point3(5f, 3f, 2f)

        assertEquals(Point3(5f, 3f, 2f), +pt)
        assertEquals(Point3(-5f, -3f, -2f), -pt)
    }
    @Test fun unary_vec() {
        val pt = Vec3(5f, 3f, 2f)

        assertEquals(Vec3(5f, 3f, 2f), +pt)
        assertEquals(Vec3(-5f, -3f, -2f), -pt)
    }
    @Test fun unary_mat() {
        val mat = Mat3(
            1f, 2f, 3f,
            4f, 5f, 6f,
            7f, 8f, 9f
        )

        assertEquals(Mat3( 1f,  2f,  3f,  4f,  5f,  6f,  7f,  8f,  9f), +mat)
        assertEquals(Mat3(-1f, -2f, -3f, -4f, -5f, -6f, -7f, -8f, -9f), -mat)
    }

    @Test fun plus_point() {
        val pt = Point3()
        val v  = Vec3(5f, 3f, 2f)

        assertEquals(Point3(5f, 3f, 2f), pt + v)
        assertEquals(Point3(5f, 3f, 2f), v + pt)

        pt += v
        assertEquals(Point3(5f, 3f, 2f), pt)
    }
    @Test fun plus_vec() {
        val v = Vec3(5f, 3f, 2f)

        assertEquals(Vec3(10f, 6f, 4f), v + v)

        v += v
        assertEquals(Vec3(10f, 6f, 4f), v)
    }
    @Test fun plus_mat() {
        val mat = Mat3(
            1f, 2f, 3f,
            4f, 5f, 6f,
            7f, 8f, 9f
        )

        assertEquals(Mat3(2f, 4f, 6f, 8f, 10f, 12f, 14f, 16f, 18f), mat + mat)

        mat += mat
        assertEquals(Mat3(2f, 4f, 6f, 8f, 10f, 12f, 14f, 16f, 18f), mat)
    }

    @Test fun minus_point() {
        val pt = Point3()
        val v  = Vec3(5f, 3f, 2f)

        assertEquals(Point3(-5f, -3f, -2f), pt - v)
        assertEquals(Point3(5f, 3f, 2f), v - pt)
        assertEquals(Vec3(2f, 4f, 1f), Point3(8f, 5f, 9f) - Point3(6f, 1f, 8f))

        pt -= v
        assertEquals(Point3(-5f, -3f, -2f), pt)
    }
    @Test fun minus_vec() {
        val v = Vec3(5f, 3f, 2f)

        assertEquals(Vec3(), v - v)

        v -= v
        assertEquals(Vec3(), v)
    }
    @Test fun minus_mat() {
        val mat = Mat3(
            1f, 2f, 3f,
            4f, 5f, 6f,
            7f, 8f, 9f
        )

        assertEquals(Mat3(), mat - mat)

        mat -= mat
        assertEquals(Mat3(), mat)
    }

    @Test fun times_vec() {
        val v = Vec3(5f, 3f, 2f)

        assertEquals(Vec3(10f, 6f, 4f), v * 2f)
        assertEquals(Vec3(10f, 6f, 4f), 2f * v)

        v *= 2f
        assertEquals(Vec3(10f, 6f, 4f), v)
    }
    @Test fun times_mat() {
        val mat = Mat3(
            1f, 2f, 3f,
            4f, 5f, 6f,
            7f, 8f, 9f
        )

        assertEquals(Mat3(2f, 4f, 6f, 8f, 10f, 12f, 14f, 16f, 18f), mat * 2f)

        mat *= 2f
        assertEquals(Mat3(2f, 4f, 6f, 8f, 10f, 12f, 14f, 16f, 18f), mat)
    }
    @Test fun times_mat_mat() {
        val m1 = Mat3(
            1f, 2f, 3f,
            4f, 5f, 6f,
            7f, 8f, 9f
        )

        val m2 = Mat3(
            9f, 8f, 7f,
            6f, 5f, 4f,
            3f, 2f, 1f
        )

        assertEquals(Mat3(
             30f,  24f,  18f,
             84f,  69f,  54f,
            138f, 114f,  90f
        ), m1 * m2)
    }
    @Test fun times_mat_vec() {
        val m = Mat3(
            1f, 2f, 3f,
            4f, 5f, 6f,
            7f, 8f, 9f
        )

        val v = Vec3(10f, 11f, 12f)

        assertEquals(Vec3(68f, 167f, 266f), m * v)
    }

    @Test fun div_vec() {
        val v = Vec3(6f, 4f, 2f)

        assertEquals(Vec3(3f, 2f, 1f), v / 2f)

        v /= 2f
        assertEquals(Vec3(3f, 2f, 1f), v)
    }
    @Test fun div_mat() {
        val mat = Mat3(
            1f, 2f, 3f,
            4f, 5f, 6f,
            7f, 8f, 9f
        )

        assertEquals(Mat3(.5f, 1f, 1.5f, 2f, 2.5f, 3f, 3.5f, 4f, 4.5f), mat / 2f)

        mat /= 2f
        assertEquals(Mat3(.5f, 1f, 1.5f, 2f, 2.5f, 3f, 3.5f, 4f, 4.5f), mat)
    }

    @Test fun norm() {
        assertEquals(1f, Vec3(0f, 1f, 0f).norm)
        assertEquals(5f, Vec3(5f, 0f, 0f).norm)
    }
    @Test fun scalar() {
        val v1 = Vec3(1f, 0f, 0f)
        val v2 = Vec3(0f, 1f, 0f)

        assertEquals(0f, v1 * v2)
        assertEquals(1f, v1 * v1)
    }
    @Test fun vect() {
        val v1 = Vec3(0f, 1f, 0f)
        val v2 = Vec3(1f, 0f, 0f)

        assertEquals(Vec3(0f, 0f, -1f), v1 vect v2)
    }
}