package net.capellari.julien.threed

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VectorInstrumentedTest {
    // Tests
    @Test fun native_2i() {
        val pt = Vec2i(5, 1)

        // equal
        assertEquals(true,  pt == Vec2i(5, 1))
        assertEquals(false, pt == Vec2i(1, 5))

        // get
        assertEquals(5, pt.x)
        assertEquals(1, pt.y)

        // set
        pt.x = 7
        assertEquals(Vec2i(7, 1), pt)

        pt.y = 4
        assertEquals(Vec2i(7, 4), pt)
    }
    @Test fun unary_2i() {
        val pt = Vec2i(5, 1)

        assertEquals(Vec2i( 5,  1), +pt)
        assertEquals(Vec2i(-5, -1), -pt)
    }
    @Test fun plus_2i() {
        val pt = Vec2i(5, 1)

        // plus
        assertEquals(Vec2i(10, 10), pt + Vec2i(5, 9))

        // plus assign
        pt += Vec2i(5, 9)
        assertEquals(Vec2i(10, 10), pt)
    }
    @Test fun minus_2i() {
        val pt = Vec2i(5, 1)

        // minus
        assertEquals(Vec2i(0, -8), pt - Vec2i(5, 9))

        // minus assign
        pt -= Vec2i(5, 9)
        assertEquals(Vec2i(0, -8), pt)
    }

    @Test fun native_2f() {
        val pt = Vec2f(5f, 1f)

        // equal
        assertEquals(true,  pt == Vec2f(5f, 1f))
        assertEquals(false, pt == Vec2f(1f, 5f))

        // get
        assertEquals(5f, pt.x)
        assertEquals(1f, pt.y)

        // set
        pt.x = 7f
        assertEquals(Vec2f(7f, 1f), pt)

        pt.y = 4f
        assertEquals(Vec2f(7f, 4f), pt)
    }
    @Test fun unary_2f() {
        val pt = Vec2f(5f, 1f)

        assertEquals(Vec2f( 5f,  1f), +pt)
        assertEquals(Vec2f(-5f, -1f), -pt)
    }
    @Test fun plus_2f() {
        val pt = Vec2f(5f, 1f)

        // plus
        assertEquals(Vec2f(10f, 10f), pt + Vec2f(5f, 9f))

        // plus assign
        pt += Vec2f(5f, 9f)
        assertEquals(Vec2f(10f, 10f), pt)
    }
    @Test fun minus_2f() {
        val pt = Vec2f(5f, 1f)

        // minus
        assertEquals(Vec2f(0f, -8f), pt - Vec2f(5f, 9f))

        // minus assign
        pt -= Vec2f(5f, 9f)
        assertEquals(Vec2f(0f, -8f), pt)
    }
}