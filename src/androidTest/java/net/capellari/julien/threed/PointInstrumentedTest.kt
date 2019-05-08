package net.capellari.julien.threed

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PointInstrumentedTest {
    // Tests
    @Test fun native_2i() {
        val pt = _Point2i(5, 1)

        // equal
        assertEquals(true,  pt == _Point2i(5, 1))
        assertEquals(false, pt == _Point2i(1, 5))

        // get
        assertEquals(5, pt.x)
        assertEquals(1, pt.y)

        // set
        pt.x = 7
        assertEquals(_Point2i(7, 1), pt)

        pt.y = 4
        assertEquals(_Point2i(7, 4), pt)
    }
    @Test fun unary_2i() {
        val pt = _Point2i(5, 1)

        assertEquals(_Point2i( 5,  1), +pt)
        assertEquals(_Point2i(-5, -1), -pt)
    }
    @Test fun plus_2i() {
        val pt = _Point2i(5, 1)

        // minus
        assertEquals(_Point2i(8, -2), pt + Vec2i(3, -3))

        // minus assign
        pt += Vec2i(3, -3)
        assertEquals(_Point2i(8, -2), pt)
    }
    @Test fun minus_2i() {
        val pt = _Point2i(5, 1)

        // minus
        assertEquals(Vec2i(0, -8), pt - _Point2i(5, 9))
        assertEquals(_Point2i(2, 4), pt - Vec2i(3, -3))

        // minus assign
        pt -= Vec2i(3, -3)
        assertEquals(_Point2i(2, 4), pt)
    }

    @Test fun native_2f() {
        val pt = Point2f(5f, 1f)

        // equal
        assertEquals(true,  pt == Point2f(5f, 1f))
        assertEquals(false, pt == Point2f(1f, 5f))

        // get
        assertEquals(5f, pt.x)
        assertEquals(1f, pt.y)

        // set
        pt.x = 7f
        assertEquals(Point2f(7f, 1f), pt)

        pt.y = 4f
        assertEquals(Point2f(7f, 4f), pt)
    }
    @Test fun unary_2f() {
        val pt = Point2f(5f, 1f)

        assertEquals(Point2f( 5f,  1f), +pt)
        assertEquals(Point2f(-5f, -1f), -pt)
    }
    @Test fun plus_2f() {
        val pt = Point2f(5f, 1f)

        // minus
        assertEquals(Point2f(8f, -2f), pt + Vec2f(3f, -3f))

        // minus assign
        pt += Vec2f(3f, -3f)
        assertEquals(Point2f(8f, -2f), pt)
    }
    @Test fun minus_2f() {
        val pt = Point2f(5f, 1f)

        // minus
        assertEquals(Vec2f(0f, -8f), pt - Point2f(5f, 9f))
        assertEquals(Point2f(2f, 4f), pt - Vec2f(3f, -3f))

        // minus assign
        pt -= Vec2f(3f, -3f)
        assertEquals(Point2f(2f, 4f), pt)
    }
}