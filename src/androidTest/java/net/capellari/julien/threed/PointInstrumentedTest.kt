package net.capellari.julien.threed

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PointInstrumentedTest {
    // Tests
    @Test fun native_2i() {
        val pt = point(5, 1)

        // equal
        assertEquals(true,  pt == point(5, 1))
        assertEquals(false, pt == point(1, 5))

        // get
        assertEquals(5, pt.x)
        assertEquals(1, pt.y)

        // set
        pt.x = 7
        assertEquals(point(7, 1), pt)

        pt.y = 4
        assertEquals(point(7, 4), pt)
    }
    @Test fun native_3i() {
        val pt = point(5, 1, 5)

        // equal
        assertEquals(true,  pt == point(5, 1, 5))
        assertEquals(false, pt == point(1, 5, 4))

        // get
        assertEquals(5, pt.x)
        assertEquals(1, pt.y)
        assertEquals(5, pt.z)

        // set
        pt.x = 7
        assertEquals(point(7, 1, 5), pt)

        pt.y = 4
        assertEquals(point(7, 4, 5), pt)

        pt.z = 3
        assertEquals(point(7, 4, 3), pt)
    }
    @Test fun native_4i() {
        val pt = point(5, 1, 5, 8)

        // equal
        assertEquals(true,  pt == point(5, 1, 5, 8))
        assertEquals(false, pt == point(1, 5, 4, 8))

        // get
        assertEquals(5, pt.x)
        assertEquals(1, pt.y)
        assertEquals(5, pt.z)
        assertEquals(8, pt.a)

        // set
        pt.x = 7
        assertEquals(point(7, 1, 5, 8), pt)

        pt.y = 4
        assertEquals(point(7, 4, 5, 8), pt)

        pt.z = 3
        assertEquals(point(7, 4, 3, 8), pt)

        pt.a = 6
        assertEquals(point(7, 4, 3, 6), pt)
    }
    @Test fun unary_2i() {
        val pt = point(5, 1)

        assertEquals(point( 5,  1), +pt)
        assertEquals(point(-5, -1), -pt)
    }
    @Test fun unary_3i() {
        val pt = point(5, 1, 5)

        assertEquals(point( 5,  1,  5), +pt)
        assertEquals(point(-5, -1, -5), -pt)
    }
    @Test fun unary_4i() {
        val pt = point(5, 1, 5, 8)

        assertEquals(point( 5,  1,  5,  8), +pt)
        assertEquals(point(-5, -1, -5, -8), -pt)
    }
    @Test fun plus_2i() {
        val pt = point(5, 1)

        // minus
        assertEquals(point(8, -2), pt + vector(3, -3))

        // minus assign
        pt += vector(3, -3)
        assertEquals(point(8, -2), pt)
    }
    @Test fun plus_3i() {
        val pt = point(5, 1, 5)

        // minus
        assertEquals(point(8, -2, 8), pt + vector(3, -3, 3))

        // minus assign
        pt += vector(3, -3, 3)
        assertEquals(point(8, -2, 8), pt)
    }
    @Test fun plus_4i() {
        val pt = point(5, 1, 5, 8)

        // minus
        assertEquals(point(8, -2, 8, 5), pt + vector(3, -3, 3, -3))

        // minus assign
        pt += vector(3, -3, 3, -3)
        assertEquals(point(8, -2, 8, 5), pt)
    }
    @Test fun minus_2i() {
        val pt = point(5, 1)

        // minus
        assertEquals(vector(0, -8), pt - point(5, 9))
        assertEquals(point(2, 4), pt - vector(3, -3))

        // minus assign
        pt -= vector(3, -3)
        assertEquals(point(2, 4), pt)
    }
    @Test fun minus_3i() {
        val pt = point(5, 1, 5)

        // minus
        assertEquals(vector(0, -8, 1), pt - point(5, 9, 4))
        assertEquals(point(2, 4, 2), pt - vector(3, -3, 3))

        // minus assign
        pt -= vector(3, -3, 3)
        assertEquals(point(2, 4, 2), pt)
    }
    @Test fun minus_4i() {
        val pt = point(5, 1, 5, 4)

        // minus
        assertEquals(vector(0, -8, 1, 2), pt - point(5, 9, 4, 2))
        assertEquals(point(2, 4, 2, 7), pt - vector(3, -3, 3, -3))

        // minus assign
        pt -= vector(3, -3, 3, -3)
        assertEquals(point(2, 4, 2, 7), pt)
    }
    @Test fun times_2i() {
        // dot product
        assertEquals(0, point(0, 1) * point(1, 0))
        assertEquals(0, point(0, 1) * vector(1, 0))
    }
    @Test fun times_3i() {
        // dot product
        assertEquals(0, point(0, 1, 0) * point(1, 0, 0))
        assertEquals(0, point(0, 1, 0) * vector(1, 0, 0))
    }
    @Test fun times_4i() {
        // dot product
        assertEquals(0, point(0, 1, 0, 0) * point(1, 0, 0, 0))
        assertEquals(0, point(0, 1, 0, 0) * vector(1, 0, 0, 0))
    }

    @Test fun native_2f() {
        val pt = point(5f, 1f)

        // equal
        assertEquals(true,  pt == point(5f, 1f))
        assertEquals(false, pt == point(1f, 5f))

        // get
        assertEquals(5f, pt.x)
        assertEquals(1f, pt.y)

        // set
        pt.x = 7f
        assertEquals(point(7f, 1f), pt)

        pt.y = 4f
        assertEquals(point(7f, 4f), pt)
    }
    @Test fun native_3f() {
        val pt = point(5f, 1f, 5f)

        // equal
        assertEquals(true,  pt == point(5f, 1f, 5f))
        assertEquals(false, pt == point(1f, 5f, 1f))

        // get
        assertEquals(5f, pt.x)
        assertEquals(1f, pt.y)
        assertEquals(5f, pt.z)

        // set
        pt.x = 7f
        assertEquals(point(7f, 1f, 5f), pt)

        pt.y = 4f
        assertEquals(point(7f, 4f, 5f), pt)

        pt.z = 9f
        assertEquals(point(7f, 4f, 9f), pt)
    }
    @Test fun native_4f() {
        val pt = point(5f, 1f, 5f, 8f)

        // equal
        assertEquals(true,  pt == point(5f, 1f, 5f, 8f))
        assertEquals(false, pt == point(1f, 5f, 1f, 8f))

        // get
        assertEquals(5f, pt.x)
        assertEquals(1f, pt.y)
        assertEquals(5f, pt.z)
        assertEquals(8f, pt.a)

        // set
        pt.x = 7f
        assertEquals(point(7f, 1f, 5f, 8f), pt)

        pt.y = 4f
        assertEquals(point(7f, 4f, 5f, 8f), pt)

        pt.z = 9f
        assertEquals(point(7f, 4f, 9f, 8f), pt)

        pt.a = 4f
        assertEquals(point(7f, 4f, 9f, 4f), pt)
    }
    @Test fun unary_2f() {
        val pt = point(5f, 1f)

        assertEquals(point( 5f,  1f), +pt)
        assertEquals(point(-5f, -1f), -pt)
    }
    @Test fun unary_3f() {
        val pt = point(5f, 1f, 5f)

        assertEquals(point( 5f,  1f,  5f), +pt)
        assertEquals(point(-5f, -1f, -5f), -pt)
    }
    @Test fun unary_4f() {
        val pt = point(5f, 1f, 5f, 8f)

        assertEquals(point( 5f,  1f,  5f,  8f), +pt)
        assertEquals(point(-5f, -1f, -5f, -8f), -pt)
    }
    @Test fun plus_2f() {
        val pt = point(5f, 1f)

        // minus
        assertEquals(point(8f, -2f), pt + vector(3f, -3f))

        // minus assign
        pt += vector(3f, -3f)
        assertEquals(point(8f, -2f), pt)
    }
    @Test fun plus_3f() {
        val pt = point(5f, 1f, 5f)

        // minus
        assertEquals(point(8f, -2f, 8f), pt + vector(3f, -3f, 3f))

        // minus assign
        pt += vector(3f, -3f, 3f)
        assertEquals(point(8f, -2f, 8f), pt)
    }
    @Test fun plus_4f() {
        val pt = point(5f, 1f, 5f, 8f)

        // minus
        assertEquals(point(8f, -2f, 8f, 5f), pt + vector(3f, -3f, 3f, -3f))

        // minus assign
        pt += vector(3f, -3f, 3f, -3f)
        assertEquals(point(8f, -2f, 8f, 5f), pt)
    }
    @Test fun minus_2f() {
        val pt = point(5f, 1f)

        // minus
        assertEquals(vector(0f, -8f), pt - point(5f, 9f))
        assertEquals(point(2f, 4f), pt - vector(3f, -3f))

        // minus assign
        pt -= vector(3f, -3f)
        assertEquals(point(2f, 4f), pt)
    }
    @Test fun minus_3f() {
        val pt = point(5f, 1f, 5f)

        // minus
        assertEquals(vector(0f, -8f, 0f), pt - point(5f, 9f, 5f))
        assertEquals(point(2f, 4f, 2f), pt - vector(3f, -3f, 3f))

        // minus assign
        pt -= vector(3f, -3f, 3f)
        assertEquals(point(2f, 4f, 2f), pt)
    }
    @Test fun minus_4f() {
        val pt = point(5f, 1f, 5f, 8f)

        // minus
        assertEquals(vector(0f, -8f, 0f, -1f), pt - point(5f, 9f, 5f, 9f))
        assertEquals(point(2f, 4f, 2f, 11f), pt - vector(3f, -3f, 3f, -3f))

        // minus assign
        pt -= vector(3f, -3f, 3f, -3f)
        assertEquals(point(2f, 4f, 2f, 11f), pt)
    }
    @Test fun times_2f() {
        // dot product
        assertEquals(0f, point(0f, 1f) * point(1f, 0f))
        assertEquals(0f, point(0f, 1f) * vector(1f, 0f))
    }
    @Test fun times_3f() {
        // dot product
        assertEquals(0f, point(0f, 1f, 0f) * point(1f, 0f, 0f))
        assertEquals(0f, point(0f, 1f, 0f) * vector(1f, 0f, 0f))
    }
    @Test fun times_4f() {
        // dot product
        assertEquals(0f, point(0f, 1f, 0f, 0f) * point(1f, 0f, 0f, 0f))
        assertEquals(0f, point(0f, 1f, 0f, 0f) * vector(1f, 0f, 0f, 0f))
    }
}