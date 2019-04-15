package net.capellari.julien.threed

import org.junit.Test
import org.junit.Assert.*

class `4DUnitTest` {
    // Tests
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

    @Test fun plus() {
        val pt = Point4()
        val v  = Vec4(5f, 3f, 2f, 7f)

        assertEquals(Point4(5f, 3f, 2f, 7f), pt + v)
        assertEquals(Point4(5f, 3f, 2f, 7f), v + pt)

        pt += v
        assertEquals(Point4(5f, 3f, 2f, 7f), pt)

        assertEquals(Vec4(10f, 6f, 4f, 14f), v + v)

        v += v
        assertEquals(Vec4(10f, 6f, 4f, 14f), v)
    }
    @Test fun minus() {
        val pt = Point4()
        val v  = Vec4(5f, 3f, 2f, 7f)

        assertEquals(Point4(-5f, -3f, -2f, -7f), pt - v)
        assertEquals(Point4( 5f,  3f,  2f,  7f), v - pt)
        assertEquals(Vec4(2f, 4f, 1f, 5f), Point4(8f, 5f, 9f, 18f) - Point4(6f, 1f, 8f, 13f))

        pt -= v
        assertEquals(Point4(-5f, -3f, -2f, -7f), pt)

        assertEquals(Vec4(), v - v)

        v -= v
        assertEquals(Vec4(), v)
    }

    @Test fun times() {
        val v = Vec4(5f, 3f, 2f, 7f)

        assertEquals(Vec4(10f, 6f, 4f, 14f), v * 2f)
        assertEquals(Vec4(10f, 6f, 4f, 14f), 2f * v)

        v *= 2f
        assertEquals(Vec4(10f, 6f, 4f, 14f), v)
    }
    @Test fun div() {
        val v = Vec4(6f, 4f, 2f, 8f)

        assertEquals(Vec4(3f, 2f, 1f, 4f), v / 2f)

        v /= 2f
        assertEquals(Vec4(3f, 2f, 1f, 4f), v)
    }

    @Test fun norm() {
        assertEquals(1f, Vec4(0f, 1f, 0f, 0f).norm)
        assertEquals(5f, Vec4(5f, 0f, 0f, 0f).norm)
    }
    @Test fun scalar() {
        val v1 = Vec4(1f, 0f, 0f, 0f)
        val v2 = Vec4(0f, 1f, 0f, 0f)

        assertEquals(0f, v1 scalar v2)
        assertEquals(1f, v1 scalar v1)
    }
}