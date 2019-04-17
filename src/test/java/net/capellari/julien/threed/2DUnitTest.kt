package net.capellari.julien.threed

import org.junit.Test
import org.junit.Assert.*

class `2DUnitTest` {
    // Tests
    @Test fun get_mat() {
        val mat = Mat2(
            1f, 2f,
            3f, 4f
        )

        assertEquals(1f, mat[0,0])
        assertEquals(2f, mat[1,0])
        assertEquals(3f, mat[0,1])
        assertEquals(4f, mat[1,1])

        assertEquals(Vec2(1f, 2f), mat.lig(0))
        assertEquals(Vec2(3f, 4f), mat.lig(1))

        assertEquals(Vec2(1f, 3f), mat.col(0))
        assertEquals(Vec2(2f, 4f), mat.col(1))
    }
    @Test fun set_mat() {
        val mat = Mat2()

        mat.lig(0, Vec2(1f, 2f))
        assertEquals(Vec2(1f, 2f), mat.lig(0))

        mat.col(0, Vec2(1f, 2f))
        assertEquals(Vec2(1f, 2f), mat.col(0))
    }

    @Test fun unary_point() {
        val pt = Point2(5f, 3f)

        assertEquals(Point2(5f, 3f),   +pt)
        assertEquals(Point2(-5f, -3f), -pt)
    }
    @Test fun unary_vec() {
        val pt = Vec2(5f, 3f)

        assertEquals(Vec2(5f, 3f),   +pt)
        assertEquals(Vec2(-5f, -3f), -pt)
    }
    @Test fun unary_mat() {
        val mat = Mat2(
            1f, 2f,
            3f, 4f
        )

        assertEquals(Mat2( 1f,  2f,  3f,  4f), +mat)
        assertEquals(Mat2(-1f, -2f, -3f, -4f), -mat)
    }

    @Test fun plus_point() {
        val pt = Point2()
        val v  = Vec2(5f, 3f)

        assertEquals(Point2(5f, 3f), pt + v)
        assertEquals(Point2(5f, 3f), v + pt)

        pt += v
        assertEquals(Point2(5f, 3f), pt)
    }
    @Test fun plus_vec() {
        val v  = Vec2(5f, 3f)

        assertEquals(Vec2(10f, 6f), v + v)

        v += v
        assertEquals(Vec2(10f, 6f), v)
    }
    @Test fun plus_mat() {
        val mat = Mat2(
            1f, 2f,
            3f, 4f
        )

        assertEquals(Mat2(2f, 4f, 6f, 8f), mat + mat)

        mat += mat
        assertEquals(Mat2(2f, 4f, 6f, 8f), mat)
    }

    @Test fun minus_point() {
        val pt = Point2()
        val v  = Vec2(5f, 3f)

        assertEquals(Point2(-5f, -3f), pt - v)
        assertEquals(Point2(5f, 3f), v - pt)
        assertEquals(Vec2(2f, 4f), Point2(8f, 5f) - Point2(6f, 1f))

        pt -= v
        assertEquals(Point2(-5f, -3f), pt)
    }
    @Test fun minus_vec() {
        val v  = Vec2(5f, 3f)

        assertEquals(Vec2(), v - v)

        v -= v
        assertEquals(Vec2(), v)
    }
    @Test fun minus_mat() {
        val mat = Mat2(
            1f, 2f,
            3f, 4f
        )

        assertEquals(Mat2(), mat - mat)

        mat -= mat
        assertEquals(Mat2(), mat)
    }

    @Test fun times_vec() {
        val v = Vec2(5f, 3f)

        assertEquals(Vec2(10f, 6f), v * 2f)
        assertEquals(Vec2(10f, 6f), 2f * v)

        v *= 2f
        assertEquals(Vec2(10f, 6f), v)
    }
    @Test fun times_mat() {
        val mat = Mat2(
            1f, 2f,
            3f, 4f
        )

        assertEquals(Mat2(2f, 4f, 6f, 8f), mat * 2f)

        mat *= 2f
        assertEquals(Mat2(2f, 4f, 6f, 8f), mat)
    }
    @Test fun times_mat_mat() {
        val m1 = Mat2(
            1f, 2f,
            3f, 4f
        )

        val m2 = Mat2(
            4f, 3f,
            2f, 1f
        )

        assertEquals(Mat2(8f, 5f, 20f, 13f), m1 * m2)
    }
    @Test fun times_mat_vec() {
        val m = Mat2(
            1f, 2f,
            3f, 4f
        )

        val v = Vec2(5f, 6f)

        assertEquals(Vec2(17f, 39f), m * v)
    }

    @Test fun div_vec() {
        val v = Vec2(6f, 4f)

        assertEquals(Vec2(3f, 2f), v / 2f)

        v /= 2f
        assertEquals(Vec2(3f, 2f), v)
    }
    @Test fun div_mat() {
        val mat = Mat2(
            1f, 2f,
            3f, 4f
        )

        assertEquals(Mat2(.5f, 1f, 1.5f, 2f), mat / 2f)

        mat /= 2f
        assertEquals(Mat2(.5f, 1f, 1.5f, 2f), mat)
    }

    @Test fun scalar() {
        val v1 = Vec2(0f, 1f)
        val v2 = Vec2(1f, 0f)

        assertEquals(0f, v1 * v2)
        assertEquals(1f, v1 * v1)
    }
    @Test fun norm() {
        assertEquals(1f, Vec2(0f, 1f).norm)
        assertEquals(5f, Vec2(5f, 0f).norm)
    }
}