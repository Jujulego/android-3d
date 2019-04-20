package net.capellari.julien.threed

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PointInstrumentedTest {
    // Tests
    @Test fun native_i2() {
        val pt = Point2i(5, 1)

        // equal
        assertEquals(true, pt == Point2i(5, 1))

        // get
        assertEquals(5, pt.x)
        assertEquals(1, pt.y)

        // set
        pt.x = 7
        assertEquals(Point2i(7, 1), pt)

        pt.y = 4
        assertEquals(Point2i(7, 4), pt)
    }
    @Test fun unary_i2() {
        val pt = Point2i(5, 1)

        assertEquals(Point2i( 5,  1), +pt)
        assertEquals(Point2i(-5, -1), -pt)
    }
}