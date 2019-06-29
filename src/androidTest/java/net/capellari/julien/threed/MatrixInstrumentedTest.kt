package net.capellari.julien.threed

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MatrixInstrumentedTest {
    // Tests
    @Test fun native_2i() {
        val mat = matrix(1, 2, 3, 4)

        // equal
        assertEquals(true,  mat == matrix(1, 2, 3, 4))
        assertEquals(false, mat == matrix(1, 5, 5, 1))

        // get data (by columns not lines)
        assertArrayEquals(intArrayOf(1, 3, 2, 4), mat.data)

        // get
        assertEquals(1, mat[0,0])
        assertEquals(2, mat[0,1])
        assertEquals(3, mat[1,0])
        assertEquals(4, mat[1,1])

        // lig
        assertEquals(vector(1, 2), mat.lig(0))
        assertEquals(vector(3, 4), mat.lig(1))

        // col
        assertEquals(vector(1, 3), mat.col(0))
        assertEquals(vector(2, 4), mat.col(1))

        // set
        mat[0,0] = 7
        assertEquals(matrix(7, 2, 3, 4), mat)

        mat[0,1] = 4
        assertEquals(matrix(7, 4, 3, 4), mat)
    }
    @Test fun coord_gen_2i() {
        val matl = Mat2i { l, c -> l }
        assertEquals(matrix(0, 0, 1, 1), matl)

        val matc = Mat2i { l, c -> c }
        assertEquals(matrix(0, 1, 0, 1), matc)
    }
    @Test fun unary_2i() {
        val mat = matrix(5, 1, 1, 5)

        assertEquals(matrix( 5,  1,  1,  5), +mat)
        assertEquals(matrix(-5, -1, -1, -5), -mat)
    }
    @Test fun plus_2i() {
        val mat = matrix(5, 1, 1, 5)

        // plus
        assertEquals(matrix(1, 2, 3, 4), mat + matrix(-4, 1, 2, -1))

        // plus assign
        mat += matrix(-4, 1, 2, -1)
        assertEquals(matrix(1, 2, 3, 4), mat)
    }
    @Test fun minus_2i() {
        val mat = matrix(5, 1, 1, 5)

        // minus
        assertEquals(matrix(1, 2, 3, 4), mat - matrix(4, -1, -2, 1))

        // minus assign
        mat -= matrix(4, -1, -2, 1)
        assertEquals(matrix(1, 2, 3, 4), mat)
    }
    @Test fun times_2i() {
        val mat = matrix(5, 1, 1, 5)

        // times
        assertEquals(matrix(10, 2, 2, 10), mat * 2)
        assertEquals(matrix(10, 2, 2, 10), 2 * mat)

        // times assign
        mat *= 2
        assertEquals(matrix(10, 2, 2, 10), mat)
    }
    @Test fun div_2i() {
        val mat = matrix(10, 2, 2, 10)

        // div
        assertEquals(matrix(5, 1, 1, 5), mat / 2)

        // div assign
        mat /= 2
        assertEquals(matrix(5, 1, 1, 5), mat)
    }
    @Test fun times_m2i() {
        var m1 = matrix(1, 2, 3, 4)
        var m2 = matrix(5, 6, 7, 8)

        assertEquals(matrix(19, 22, 43, 50), m1 * m2)
        assertEquals(matrix(23, 34, 31, 46), m2 * m1)

        m1 *= m2
        assertEquals(matrix(19, 22, 43, 50), m1)

        m1 = matrix(1, 2, 3, 4)
        m2 *= m1
        assertEquals(matrix(23, 34, 31, 46), m2)
    }
    @Test fun times_v2i() {
        val mat = matrix(1, 2, 3, 4)
        val v = vector(4, 7)

        assertEquals(vector(18, 40), mat * v)
        assertEquals(vector(25, 36), v * mat)
    }

    @Test fun scale_4i() {
        val mat = scale(2, 2, 2)
        val pt = point(1, 1, 1)

        assertEquals(point(2, 2, 2), pt * mat)
    }
    @Test fun scale_4f() {
        val mat = scale(2f, 2f, 2f)
        val pt = point(1f, 1f, 1f)

        assertEquals(point(2f, 2f, 2f), pt * mat)
    }

    @Test fun translate_4i() {
        val mat = translate(1, 2, 3)
        val pt = point(1, 1, 1)

        assertEquals(point(2, 3, 4), pt * mat)
    }
    @Test fun translate_4f() {
        val mat = translate(1f, 2f, 3f)
        val pt = point(1f, 1f, 1f)

        assertEquals(point(2f, 3f, 4f), pt * mat)
    }

    @Test fun rotate_4i() {
        val pt = point(1, 1, 1)

        // clkw = clock-wise    cclkw = counter clock-wise
        assertEquals(point( 1, -1,  1), pt * rotate(-90.0, 1, 0, 0)) // 90 clkw x
        assertEquals(point( 1,  1, -1), pt * rotate(-90.0, 0, 1, 0)) // 90 clkw y
        assertEquals(point( 1, -1,  1), pt * rotate(-90.0, 0, 0, 1)) // 90 clkw z

        assertEquals(point( 1,  1, -1), pt * rotate( 90.0, 1, 0, 0)) // 90 cclkw x
        assertEquals(point(-1,  1,  1), pt * rotate( 90.0, 0, 1, 0)) // 90 cclkw y
        assertEquals(point(-1,  1,  1), pt * rotate( 90.0, 0, 0, 1)) // 90 cclkw z
    }
    @Test fun rotate_4f() {
        val pt = point(1f, 1f, 1f)

        // clkw = clock-wise    cclkw = counter clock-wise
        assertEquals(point( 1f, -1f,  1f), pt * rotate(-90.0, 1f, 0f, 0f)) // 90 clkw x
        assertEquals(point( 1f,  1f, -1f), pt * rotate(-90.0, 0f, 1f, 0f)) // 90 clkw y
        assertEquals(point( 1f, -1f,  1f), pt * rotate(-90.0, 0f, 0f, 1f)) // 90 clkw z

        assertEquals(point( 1f,  1f, -1f), pt * rotate( 90.0, 1f, 0f, 0f)) // 90 cclkw x
        assertEquals(point(-1f,  1f,  1f), pt * rotate( 90.0, 0f, 1f, 0f)) // 90 cclkw y
        assertEquals(point(-1f,  1f,  1f), pt * rotate( 90.0, 0f, 0f, 1f)) // 90 cclkw z
    }
}