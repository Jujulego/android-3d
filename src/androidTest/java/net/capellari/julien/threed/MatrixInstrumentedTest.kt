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

        // get data
        assertEquals(true, intArrayOf(1, 2, 3, 4).contentEquals(mat.data))

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
    @Test fun times_v2i() {
        val mat = matrix(1, 2, 3, 4)
        val v = vector(4, 7)

        assertEquals(vector(18, 40), mat * v)
        assertEquals(vector(25, 36), v * mat)
    }
    @Test fun times_p2i() {
        val mat = matrix(1, 2, 3, 4)
        val v = point(4, 7)

        assertEquals(point(18, 40), mat * v)
    }
}