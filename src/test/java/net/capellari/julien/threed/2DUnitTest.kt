package net.capellari.julien.threed

import org.junit.Test
import org.junit.Assert.*

class `2DUnitTest` {
    // Tests
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

    @Test fun plus() {
        val pt = Point2()
        val v  = Vec2(5f, 3f)

        assertEquals(Point2(5f, 3f), pt + v)
        assertEquals(Point2(5f, 3f), v + pt)

        pt += v
        assertEquals(Point2(5f, 3f), pt)

        assertEquals(Vec2(10f, 6f), v + v)

        v += v
        assertEquals(Vec2(10f, 6f), v)
    }
    @Test fun minus() {
        val pt = Point2()
        val v  = Vec2(5f, 3f)

        assertEquals(Point2(-5f, -3f), pt - v)
        assertEquals(Point2(5f, 3f), v - pt)
        assertEquals(
            Vec2(2f, 4f), Point2(
                8f,
                5f
            ) - Point2(6f, 1f)
        )

        pt -= v
        assertEquals(Point2(-5f, -3f), pt)

        assertEquals(Vec2(), v - v)

        v -= v
        assertEquals(Vec2(), v)
    }

    @Test fun times() {
        val v = Vec2(5f, 3f)

        assertEquals(Vec2(10f, 6f), v * 2f)
        assertEquals(Vec2(10f, 6f), 2f * v)

        v *= 2f
        assertEquals(Vec2(10f, 6f), v)
    }
    @Test fun div() {
        val v = Vec2(6f, 4f)

        assertEquals(Vec2(3f, 2f), v / 2f)

        v /= 2f
        assertEquals(Vec2(3f, 2f), v)
    }

    @Test fun scalar() {
        val v1 = Vec2(0f, 1f)
        val v2 = Vec2(1f, 0f)

        assertEquals(0f, v1 scalar v2)
        assertEquals(1f, v1 scalar v1)
    }
    @Test fun norm() {
        assertEquals(1f, Vec2(0f, 1f).norm)
        assertEquals(5f, Vec2(5f, 0f).norm)
    }
}