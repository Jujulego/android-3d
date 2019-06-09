package net.capellari.julien.threed

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VectorInstrumentedTest {
    // Tests
    @Test fun native_2i() {
        val pt = vector(5, 1)

        // equal
        assertEquals(true,  pt == vector(5, 1))
        assertEquals(false, pt == vector(1, 5))

        // get
        assertEquals(5, pt.x)
        assertEquals(1, pt.y)

        // set
        pt.x = 7
        assertEquals(vector(7, 1), pt)

        pt.y = 4
        assertEquals(vector(7, 4), pt)
    }
    @Test fun native_3i() {
        val pt = vector(5, 1, 5)

        // equal
        assertEquals(true,  pt == vector(5, 1, 5))
        assertEquals(false, pt == vector(1, 5, 1))

        // get
        assertEquals(5, pt.x)
        assertEquals(1, pt.y)
        assertEquals(5, pt.z)

        // set
        pt.x = 7
        assertEquals(vector(7, 1, 5), pt)

        pt.y = 4
        assertEquals(vector(7, 4, 5), pt)

        pt.z = 8
        assertEquals(vector(7, 4, 8), pt)
    }
    @Test fun native_4i() {
        val pt = vector(5, 1, 5, 8)

        // equal
        assertEquals(true,  pt == vector(5, 1, 5, 8))
        assertEquals(false, pt == vector(1, 5, 1, 8))

        // get
        assertEquals(5, pt.x)
        assertEquals(1, pt.y)
        assertEquals(5, pt.z)
        assertEquals(8, pt.a)

        // set
        pt.x = 7
        assertEquals(vector(7, 1, 5, 8), pt)

        pt.y = 4
        assertEquals(vector(7, 4, 5, 8), pt)

        pt.z = 8
        assertEquals(vector(7, 4, 8, 8), pt)

        pt.a = 1
        assertEquals(vector(7, 4, 8, 1), pt)
    }
    @Test fun unary_2i() {
        val pt = vector(5, 1)

        assertEquals(vector( 5,  1), +pt)
        assertEquals(vector(-5, -1), -pt)
    }
    @Test fun unary_3i() {
        val pt = vector(5, 1, 5)

        assertEquals(vector( 5,  1,  5), +pt)
        assertEquals(vector(-5, -1, -5), -pt)
    }
    @Test fun unary_4i() {
        val pt = vector(5, 1, 5, 8)

        assertEquals(vector( 5,  1,  5,  8), +pt)
        assertEquals(vector(-5, -1, -5, -8), -pt)
    }
    @Test fun plus_2i() {
        val pt = vector(5, 1)

        // plus
        assertEquals(vector(10, 10), pt + vector(5, 9))

        // plus assign
        pt += vector(5, 9)
        assertEquals(vector(10, 10), pt)
    }
    @Test fun plus_3i() {
        val pt = vector(5, 1, 5)

        // plus
        assertEquals(vector(10, 10, 10), pt + vector(5, 9, 5))

        // plus assign
        pt += vector(5, 9, 5)
        assertEquals(vector(10, 10, 10), pt)
    }
    @Test fun plus_4i() {
        val pt = vector(5, 1, 5, 8)

        // plus
        assertEquals(vector(10, 10, 10, 10), pt + vector(5, 9, 5, 2))

        // plus assign
        pt += vector(5, 9, 5, 2)
        assertEquals(vector(10, 10, 10, 10), pt)
    }
    @Test fun minus_2i() {
        val pt = vector(5, 1)

        // minus
        assertEquals(vector(0, -8), pt - vector(5, 9))

        // minus assign
        pt -= vector(5, 9)
        assertEquals(vector(0, -8), pt)
    }
    @Test fun minus_3i() {
        val pt = vector(5, 1, 5)

        // minus
        assertEquals(vector(0, -8, 0), pt - vector(5, 9, 5))

        // minus assign
        pt -= vector(5, 9, 5)
        assertEquals(vector(0, -8, 0), pt)
    }
    @Test fun minus_4i() {
        val pt = vector(5, 1, 5, 8)

        // minus
        assertEquals(vector(0, -8, 0, 6), pt - vector(5, 9, 5, 2))

        // minus assign
        pt -= vector(5, 9, 5, 2)
        assertEquals(vector(0, -8, 0, 6), pt)
    }
    @Test fun times_2i() {
        val v = vector(8, 12)

        // times
        assertEquals(vector(16, 24), v * 2)
        assertEquals(vector(16, 24), 2 * v)

        // times assign
        v *= 2
        assertEquals(vector(16, 24), v)
    }
    @Test fun times_3i() {
        val v = vector(8, 12, 5)

        // times
        assertEquals(vector(16, 24, 10), v * 2)
        assertEquals(vector(16, 24, 10), 2 * v)

        // times assign
        v *= 2
        assertEquals(vector(16, 24, 10), v)
    }
    @Test fun times_4i() {
        val v = vector(8, 12, 5, 8)

        // times
        assertEquals(vector(16, 24, 10, 16), v * 2)
        assertEquals(vector(16, 24, 10, 16), 2 * v)

        // times assign
        v *= 2
        assertEquals(vector(16, 24, 10, 16), v)
    }
    @Test fun div_2i() {
        val v = vector(8, 12)

        // div
        assertEquals(vector(4, 6), v / 2)

        // div assign
        v /= 2
        assertEquals(vector(4, 6), v)
    }
    @Test fun div_3i() {
        val v = vector(8, 12, 6)

        // div
        assertEquals(vector(4, 6, 3), v / 2)

        // div assign
        v /= 2
        assertEquals(vector(4, 6, 3), v)
    }
    @Test fun div_4i() {
        val v = vector(8, 12, 6, 24)

        // div
        assertEquals(vector(4, 6, 3, 12), v / 2)

        // div assign
        v /= 2
        assertEquals(vector(4, 6, 3, 12), v)
    }
    @Test fun scalar_2i() {
        // times (scalar)
        assertEquals(44, vector(4, 6) * vector(8, 2))
    }
    @Test fun scalar_3i() {
        // times (scalar)
        assertEquals(0, vector(1, 0, 0) * vector(0, 1, 0))
    }
    @Test fun scalar_4i() {
        // times (scalar)
        assertEquals(0, vector(1, 0, 0, 0) * vector(0, 1, 0, 1))
    }
    @Test fun cross_3i() {
        // times (scalar)
        assertEquals(vector(0, 0, 1), vector(1, 0, 0) cross vector(0, 1, 0))
    }

    @Test fun native_2f() {
        val pt = vector(5f, 1f)

        // equal
        assertEquals(true,  pt == vector(5f, 1f))
        assertEquals(false, pt == vector(1f, 5f))

        // get
        assertEquals(5f, pt.x)
        assertEquals(1f, pt.y)

        // set
        pt.x = 7f
        assertEquals(vector(7f, 1f), pt)

        pt.y = 4f
        assertEquals(vector(7f, 4f), pt)
    }
    @Test fun native_3f() {
        val pt = vector(5f, 1f, 8f)

        // equal
        assertEquals(true,  pt == vector(5f, 1f, 8f))
        assertEquals(false, pt == vector(1f, 5f, 8f))

        // get
        assertEquals(5f, pt.x)
        assertEquals(1f, pt.y)
        assertEquals(8f, pt.z)

        // set
        pt.x = 7f
        assertEquals(vector(7f, 1f, 8f), pt)

        pt.y = 4f
        assertEquals(vector(7f, 4f, 8f), pt)

        pt.z = 2f
        assertEquals(vector(7f, 4f, 2f), pt)
    }
    @Test fun native_4f() {
        val pt = vector(5f, 1f, 8f, 4f)

        // equal
        assertEquals(true,  pt == vector(5f, 1f, 8f, 4f))
        assertEquals(false, pt == vector(1f, 5f, 8f, 4f))

        // get
        assertEquals(5f, pt.x)
        assertEquals(1f, pt.y)
        assertEquals(8f, pt.z)
        assertEquals(4f, pt.a)

        // set
        pt.x = 7f
        assertEquals(vector(7f, 1f, 8f, 4f), pt)

        pt.y = 4f
        assertEquals(vector(7f, 4f, 8f, 4f), pt)

        pt.z = 2f
        assertEquals(vector(7f, 4f, 2f, 4f), pt)

        pt.a = 5f
        assertEquals(vector(7f, 4f, 2f, 5f), pt)
    }
    @Test fun unary_2f() {
        val pt = vector(5f, 1f)

        assertEquals(vector( 5f,  1f), +pt)
        assertEquals(vector(-5f, -1f), -pt)
    }
    @Test fun unary_3f() {
        val pt = vector(5f, 1f, 4f)

        assertEquals(vector( 5f,  1f,  4f), +pt)
        assertEquals(vector(-5f, -1f, -4f), -pt)
    }
    @Test fun unary_4f() {
        val pt = vector(5f, 1f, 4f, 7f)

        assertEquals(vector( 5f,  1f,  4f,  7f), +pt)
        assertEquals(vector(-5f, -1f, -4f, -7f), -pt)
    }
    @Test fun plus_2f() {
        val pt = vector(5f, 1f)

        // plus
        assertEquals(vector(10f, 10f), pt + vector(5f, 9f))

        // plus assign
        pt += vector(5f, 9f)
        assertEquals(vector(10f, 10f), pt)
    }
    @Test fun plus_3f() {
        val pt = vector(5f, 1f, 4f)

        // plus
        assertEquals(vector(10f, 10f, 10f), pt + vector(5f, 9f, 6f))

        // plus assign
        pt += vector(5f, 9f, 6f)
        assertEquals(vector(10f, 10f, 10f), pt)
    }
    @Test fun plus_4f() {
        val pt = vector(5f, 1f, 4f, 7f)

        // plus
        assertEquals(vector(10f, 10f, 10f, 10f), pt + vector(5f, 9f, 6f, 3f))

        // plus assign
        pt += vector(5f, 9f, 6f, 3f)
        assertEquals(vector(10f, 10f, 10f, 10f), pt)
    }
    @Test fun minus_2f() {
        val pt = vector(5f, 1f)

        // minus
        assertEquals(vector(0f, -8f), pt - vector(5f, 9f))

        // minus assign
        pt -= vector(5f, 9f)
        assertEquals(vector(0f, -8f), pt)
    }
    @Test fun minus_3f() {
        val pt = vector(5f, 1f, 4f)

        // minus
        assertEquals(vector(0f, -8f, -2f), pt - vector(5f, 9f, 6f))

        // minus assign
        pt -= vector(5f, 9f, 6f)
        assertEquals(vector(0f, -8f, -2f), pt)
    }
    @Test fun minus_4f() {
        val pt = vector(5f, 1f, 4f, 7f)

        // minus
        assertEquals(vector(0f, -8f, -2f, 4f), pt - vector(5f, 9f, 6f, 3f))

        // minus assign
        pt -= vector(5f, 9f, 6f, 3f)
        assertEquals(vector(0f, -8f, -2f, 4f), pt)
    }
    @Test fun times_2f() {
        val v = vector(8f, 12f)

        // times
        assertEquals(vector(16f, 24f), v  * 2f)
        assertEquals(vector(16f, 24f), 2f * v)

        // times assign
        v *= 2f
        assertEquals(vector(16f, 24f), v)
    }
    @Test fun times_3f() {
        val v = vector(8f, 12f, 85f)

        // times
        assertEquals(vector(16f, 24f, 170f), v  * 2f)
        assertEquals(vector(16f, 24f, 170f), 2f * v)

        // times assign
        v *= 2f
        assertEquals(vector(16f, 24f, 170f), v)
    }
    @Test fun times_4f() {
        val v = vector(8f, 12f, 85f, 7f)

        // times
        assertEquals(vector(16f, 24f, 170f, 14f), v  * 2f)
        assertEquals(vector(16f, 24f, 170f, 14f), 2f * v)

        // times assign
        v *= 2f
        assertEquals(vector(16f, 24f, 170f, 14f), v)
    }
    @Test fun div_2f() {
        val v = vector(8f, 12f)

        // div
        assertEquals(vector(4f, 6f), v / 2f)

        // div assign
        v /= 2f
        assertEquals(vector(4f, 6f), v)
    }
    @Test fun div_3f() {
        val v = vector(8f, 12f, 5f)

        // div
        assertEquals(vector(4f, 6f, 2.5f), v / 2f)

        // div assign
        v /= 2f
        assertEquals(vector(4f, 6f, 2.5f), v)
    }
    @Test fun div_4f() {
        val v = vector(8f, 12f, 5f, 77f)

        // div
        assertEquals(vector(4f, 6f, 2.5f, 38.5f), v / 2f)

        // div assign
        v /= 2f
        assertEquals(vector(4f, 6f, 2.5f, 38.5f), v)
    }
    @Test fun scalar_2f() {
        // times (scalar)
        assertEquals(44f, vector(4f, 6f) * vector(8f, 2f))
    }
    @Test fun scalar_3f() {
        // times (scalar)
        assertEquals(0f, vector(1f, 0f, 0f) * vector(0f, 1f, 0f))
    }
    @Test fun scalar_4f() {
        // times (scalar)
        assertEquals(0f, vector(1f, 0f, 0f, 0f) * vector(0f, 1f, 0f, 1f))
    }
    @Test fun cross_3f() {
        // times (scalar)
        assertEquals(vector(0f, 0f, 1f), vector(1f, 0f, 0f) cross vector(0f, 1f, 0f))
    }
}