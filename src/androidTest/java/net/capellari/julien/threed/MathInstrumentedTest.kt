package net.capellari.julien.threed

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MathInstrumentedTest {
    // Tests
    @Test fun native_i2() {
        val pt = Point2i(5, 1)

        assertEquals(5, pt.x)
        assertEquals(1, pt.y)
    }

    @Test fun times_mat_mat() {
        val m1 = Mat4(
             1f,  2f,  3f,  4f,
             5f,  6f,  7f,  8f,
             9f, 10f, 11f, 12f,
            13f, 14f, 15f, 16f
        )

        val m2 = Mat4(
            16f, 15f, 14f, 13f,
            12f, 11f, 10f,  9f,
             8f,  7f,  6f,  5f,
             4f,  3f,  2f,  1f
        )

        assertEquals(Mat4(
             80f,  70f,  60f,  50f,
            240f, 214f, 188f, 162f,
            400f, 358f, 316f, 274f,
            560f, 502f, 444f, 386f
        ), m1 * m2)
    }
    @Test fun times_mat_vec() {
        val m = Mat4(
             1f,  2f,  3f,  4f,
             5f,  6f,  7f,  8f,
             9f, 10f, 11f, 12f,
            13f, 14f, 15f, 16f
        )

        val v = Vec4(17f, 18f, 19f, 20f)

        assertEquals(Vec4(190f, 486f, 782f, 1078f), m * v)
    }
}