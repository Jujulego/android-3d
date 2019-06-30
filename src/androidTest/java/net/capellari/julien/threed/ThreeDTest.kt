package net.capellari.julien.threed

import android.opengl.Matrix
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ThreeDTest {
    @Test fun scale_4i() {
        val mat = scale(2, 2, 2)
        val pt = point(1, 1, 1)

        assertEquals(point(2, 2, 2), mat * pt)
        assertEquals(mat, scale(vector(2, 2, 2)))
    }
    @Test fun translate_4i() {
        val mat = translate(1, 2, 3)
        val pt = point(1, 1, 1)

        assertEquals(point(2, 3, 4), mat * pt)
        assertEquals(mat, translate(vector(1, 2, 3)))
    }
    @Test fun rotate_4i() {
        val pt = point(1, 1, 1)

        // clkw = clock-wise    cclkw = counter clock-wise
        assertEquals(point(1, -1, 1), rotate( 90.0, 1, 0, 0) * pt) // 90 cclkw x
        assertEquals(point(1, 1, -1), rotate( 90.0, 0, 1, 0) * pt) // 90 cclkw y
        assertEquals(point(1, -1, 1), rotate( 90.0, 0, 0, 1) * pt) // 90 cclkw z

        assertEquals(point(1, 1, -1), rotate(-90.0, 1, 0, 0) * pt) // 90 clkw x
        assertEquals(point(-1, 1, 1), rotate(-90.0, 0, 1, 0) * pt) // 90 clkw y
        assertEquals(point(-1, 1, 1), rotate(-90.0, 0, 0, 1) * pt) // 90 clkw z

        assertEquals(rotate(90.0, 1, 0, 0), rotate(90.0, vector(1, 0, 0)))
    }

    @Test fun scale_4f() {
        val mat = scale(2f, 2f, 2f)
        val pt = point(1f, 1f, 1f)

        assertEquals(point(2f, 2f, 2f), mat * pt)
        assertEquals(mat, scale(vector(2f, 2f, 2f)))
    }
    @Test fun translate_4f() {
        val mat = translate(1f, 2f, 3f)
        val pt = point(1f, 1f, 1f)

        assertEquals(point(2f, 3f, 4f), mat * pt)
        assertEquals(mat, translate(vector(1f, 2f, 3f)))
    }
    @Test fun rotate_4f() {
        val pt = point(1f, 1f, 1f)

        // clkw = clock-wise    cclkw = counter clock-wise
        assertEquals(point(1f, -1f, 1f), rotate( 90.0, 1f, 0f, 0f) * pt) // 90 cclkw x
        assertEquals(point(1f, 1f, -1f), rotate( 90.0, 0f, 1f, 0f) * pt) // 90 cclkw y
        assertEquals(point(1f, -1f, 1f), rotate( 90.0, 0f, 0f, 1f) * pt) // 90 cclkw z

        assertEquals(point(1f, 1f, -1f), rotate(-90.0, 1f, 0f, 0f) * pt) // 90  clkw x
        assertEquals(point(-1f, 1f, 1f), rotate(-90.0, 0f, 1f, 0f) * pt) // 90  clkw y
        assertEquals(point(-1f, 1f, 1f), rotate(-90.0, 0f, 0f, 1f) * pt) // 90  clkw z

        assertEquals(rotate(90.0, 1f, 0f, 0f), rotate(90.0, vector(1f, 0f, 0f)))
    }

    @Test fun lookAt_4f() {
        val opengl = FloatArray(16) { 0f }
        Matrix.setLookAtM(opengl, 0,
            5f, 4f, 7f,
            2f, 8f, 8f,
            0f, 0f, 1f
        )

        val mat = lookAt(
            vector(5f, 4f, 7f),
            vector(2f, 8f, 8f),
            vector(0f, 0f, 1f)
        )

        assertArrayEquals(opengl, mat.data, 0.000001f)
    }
}