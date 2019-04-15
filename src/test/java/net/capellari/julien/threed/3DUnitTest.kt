package net.capellari.julien.threed

import org.junit.Test
import org.junit.Assert.*

class `3DUnitTest` {
    // Tests
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

    @Test fun plus() {
        val pt = Point3()
        val v  = Vec3(5f, 3f, 2f)

        assertEquals(Point3(5f, 3f, 2f), pt + v)
        assertEquals(Point3(5f, 3f, 2f), v + pt)

        pt += v
        assertEquals(Point3(5f, 3f, 2f), pt)

        assertEquals(Vec3(10f, 6f, 4f), v + v)

        v += v
        assertEquals(Vec3(10f, 6f, 4f), v)
    }
    @Test fun minus() {
        val pt = Point3()
        val v  = Vec3(5f, 3f, 2f)

        assertEquals(Point3(-5f, -3f, -2f), pt - v)
        assertEquals(Point3(5f, 3f, 2f), v - pt)
        assertEquals(
            Vec3(2f, 4f, 1f), Point3(
                8f,
                5f,
                9f
            ) - Point3(6f, 1f, 8f)
        )

        pt -= v
        assertEquals(Point3(-5f, -3f, -2f), pt)

        assertEquals(Vec3(), v - v)

        v -= v
        assertEquals(Vec3(), v)
    }

    @Test fun times() {
        val v = Vec3(5f, 3f, 2f)

        assertEquals(Vec3(10f, 6f, 4f), v * 2f)
        assertEquals(Vec3(10f, 6f, 4f), 2f * v)

        v *= 2f
        assertEquals(Vec3(10f, 6f, 4f), v)
    }
    @Test fun div() {
        val v = Vec3(6f, 4f, 2f)

        assertEquals(Vec3(3f, 2f, 1f), v / 2f)

        v /= 2f
        assertEquals(Vec3(3f, 2f, 1f), v)
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