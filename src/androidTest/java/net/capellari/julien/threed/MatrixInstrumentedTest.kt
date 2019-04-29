package net.capellari.julien.threed

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MatrixInstrumentedTest {
    // Tests
    @Test fun native_2i() {
        val mat = Mat2i(
            5, 1,
            1, 5
        )

        // equal
        assertEquals(true,  mat == Mat2i(5, 1, 1, 5))
        assertEquals(false, mat == Mat2i(1, 5, 5, 1))

        // get data
        assertEquals(true, intArrayOf(5, 1, 1, 5).contentEquals(mat.data))

        // get
        assertEquals(5, mat[0,0])
        assertEquals(1, mat[0,1])
        assertEquals(1, mat[1,0])
        assertEquals(5, mat[1,1])

        // set
        mat[0,0] = 7
        assertEquals(Mat2i(7, 1, 1, 5), mat)

        mat[0,1] = 4
        assertEquals(Mat2i(7, 1, 4, 5), mat)
    }
    @Test fun unary_2i() {
        val mat = Mat2i(5, 1, 1, 5)

        assertEquals(Mat2i( 5,  1,  1,  5), +mat)
        assertEquals(Mat2i(-5, -1, -1, -5), -mat)
    }
    @Test fun plus_2i() {
        val mat = Mat2i(5, 1, 1, 5)

        // plus
        assertEquals(Mat2i(1, 2, 3, 4), mat + Mat2i(-4, 1, 2, -1))

        // plus assign
        mat += Mat2i(-4, 1, 2, -1)
        assertEquals(Mat2i(1, 2, 3, 4), mat)
    }
    @Test fun minus_2i() {
        val mat = Mat2i(5, 1, 1, 5)

        // minus
        assertEquals(Mat2i(1, 2, 3, 4), mat - Mat2i(4, -1, -2, 1))

        // minus assign
        mat -= Mat2i(4, -1, -2, 1)
        assertEquals(Mat2i(1, 2, 3, 4), mat)
    }
    @Test fun times_2i() {
        val mat = Mat2i(5, 1, 1, 5)

        // times
        assertEquals(Mat2i(10, 2, 2, 10), mat * 2)
        assertEquals(Mat2i(10, 2, 2, 10), 2 * mat)

        // times assign
        mat *= 2
        assertEquals(Mat2i(10, 2, 2, 10), mat)
    }
    @Test fun div_2i() {
        val mat = Mat2i(10, 2, 2, 10)

        // div
        assertEquals(Mat2i(5, 1, 1, 5), mat / 2)

        // div assign
        mat /= 2
        assertEquals(Mat2i(5, 1, 1, 5), mat)
    }
}