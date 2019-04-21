package net.capellari.julien.threed

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PointInstrumentedTest {
    // Tests
    @Test fun native_2i() {
        val pt = Point2i(5, 1)

        // equal
        assertEquals(true,  pt == Point2i(5, 1))
        assertEquals(false, pt == Point2i(1, 5))

        // get
        assertEquals(5, pt.x)
        assertEquals(1, pt.y)

        // set
        pt.x = 7
        assertEquals(Point2i(7, 1), pt)

        pt.y = 4
        assertEquals(Point2i(7, 4), pt)
    }
    @Test fun unary_2i() {
        val pt = Point2i(5, 1)

        assertEquals(Point2i( 5,  1), +pt)
        assertEquals(Point2i(-5, -1), -pt)
    }
    @Test fun plus_2i() {
        val pt = Point2i(5, 1)

        // plus
        assertEquals(Point2i(10, 10), pt + Point2i(5, 9))

        // plus assign
        pt += Point2i(5, 9)
        assertEquals(Point2i(10, 10), pt)
    }
    @Test fun minus_2i() {
        val pt = Point2i(5, 1)

        // minus
        assertEquals(Point2i(0, -8), pt - Point2i(5, 9))

        // minus assign
        pt -= Point2i(5, 9)
        assertEquals(Point2i(0, -8), pt)
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

        // plus
        assertEquals(Point2f(10f, 10f), pt + Point2f(5f, 9f))

        // plus assign
        pt += Point2f(5f, 9f)
        assertEquals(Point2f(10f, 10f), pt)
    }
    @Test fun minus_2f() {
        val pt = Point2f(5f, 1f)

        // minus
        assertEquals(Point2f(0f, -8f), pt - Point2f(5f, 9f))

        // minus assign
        pt -= Point2f(5f, 9f)
        assertEquals(Point2f(0f, -8f), pt)
    }
}