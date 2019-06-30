package net.capellari.julien.threed

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MatrixInstrumentedTest {
    // Tests (i)
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
    @Test fun native_3i() {
        val mat = matrix(
            1, 2, 3,
            4, 5, 6,
            7, 8, 9
        )

        // equal
        assertEquals(true,  mat == matrix(1, 2, 3, 4, 5, 6, 7, 8, 9))
        assertEquals(false, mat == matrix(1, 5, 5, 1, 3, 5, 1, 7, 6))

        // get data (by columns not lines)
        assertArrayEquals(intArrayOf(1, 4, 7, 2, 5, 8, 3, 6, 9), mat.data)

        // get
        assertEquals(1, mat[0,0])
        assertEquals(2, mat[0,1])
        assertEquals(3, mat[0,2])
        assertEquals(4, mat[1,0])
        assertEquals(5, mat[1,1])
        assertEquals(6, mat[1,2])
        assertEquals(7, mat[2,0])
        assertEquals(8, mat[2,1])
        assertEquals(9, mat[2,2])

        // lig
        assertEquals(vector(1, 2, 3), mat.lig(0))
        assertEquals(vector(4, 5, 6), mat.lig(1))
        assertEquals(vector(7, 8, 9), mat.lig(2))

        // col
        assertEquals(vector(1, 4, 7), mat.col(0))
        assertEquals(vector(2, 5, 8), mat.col(1))
        assertEquals(vector(3, 6, 9), mat.col(2))

        // set
        mat[0,0] = 7
        assertEquals(matrix(7, 2, 3, 4, 5, 6, 7, 8, 9), mat)

        mat[0,1] = 4
        assertEquals(matrix(7, 4, 3, 4, 5, 6, 7, 8, 9), mat)

        mat[2,1] = 1
        assertEquals(matrix(7, 4, 3, 4, 5, 6, 7, 1, 9), mat)
    }
    @Test fun native_4i() {
        val mat = matrix(
             1,  2,  3,  4,
             5,  6,  7,  8,
             9, 10, 11, 12,
            13, 14, 15, 16
        )

        // equal
        assertEquals(true,  mat == matrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16))
        assertEquals(false, mat == matrix(1, 5, 5, 1, 3, 5, 1, 7, 6, 7, 5, 2, 1, 4, 5, 8))

        // get data (by columns not lines)
        assertArrayEquals(intArrayOf(1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15, 4, 8, 12, 16), mat.data)

        // get
        assertEquals( 1, mat[0,0])
        assertEquals( 2, mat[0,1])
        assertEquals( 3, mat[0,2])
        assertEquals( 4, mat[0,3])
        assertEquals( 5, mat[1,0])
        assertEquals( 6, mat[1,1])
        assertEquals( 7, mat[1,2])
        assertEquals( 8, mat[1,3])
        assertEquals( 9, mat[2,0])
        assertEquals(10, mat[2,1])
        assertEquals(11, mat[2,2])
        assertEquals(12, mat[2,3])
        assertEquals(13, mat[3,0])
        assertEquals(14, mat[3,1])
        assertEquals(15, mat[3,2])
        assertEquals(16, mat[3,3])

        // lig
        assertEquals(vector( 1,  2,  3,  4), mat.lig(0))
        assertEquals(vector( 5,  6,  7,  8), mat.lig(1))
        assertEquals(vector( 9, 10, 11, 12), mat.lig(2))
        assertEquals(vector(13, 14, 15, 16), mat.lig(3))

        // col
        assertEquals(vector(1, 5,  9, 13), mat.col(0))
        assertEquals(vector(2, 6, 10, 14), mat.col(1))
        assertEquals(vector(3, 7, 11, 15), mat.col(2))
        assertEquals(vector(4, 8, 12, 16), mat.col(3))

        // set
        mat[0,0] = 7
        assertEquals(matrix(7, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16), mat)

        mat[0,1] = 4
        assertEquals(matrix(7, 4, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16), mat)

        mat[2,1] = 1
        assertEquals(matrix(7, 4, 3, 4, 5, 6, 7, 8, 9, 1, 11, 12, 13, 14, 15, 16), mat)

        mat[3,3] = 8
        assertEquals(matrix(7, 4, 3, 4, 5, 6, 7, 8, 9, 1, 11, 12, 13, 14, 15, 8), mat)
    }
    @Test fun coord_gen_2i() {
        val matl = Mat2i { l, _ -> l }
        assertEquals(matrix(0, 0, 1, 1), matl)

        val matc = Mat2i { _, c -> c }
        assertEquals(matrix(0, 1, 0, 1), matc)
    }
    @Test fun coord_gen_3i() {
        val matl = Mat3i { l, _ -> l }
        assertEquals(matrix(0, 0, 0, 1, 1, 1, 2, 2, 2), matl)

        val matc = Mat3i { _, c -> c }
        assertEquals(matrix(0, 1, 2, 0, 1, 2, 0, 1, 2), matc)
    }
    @Test fun coord_gen_4i() {
        val matl = Mat4i { l, _ -> l }
        assertEquals(matrix(0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3), matl)

        val matc = Mat4i { _, c -> c }
        assertEquals(matrix(0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3), matc)
    }
    @Test fun unary_2i() {
        val mat = matrix(5, 1, 1, 5)

        assertEquals(matrix( 5,  1,  1,  5), +mat)
        assertEquals(matrix(-5, -1, -1, -5), -mat)
    }
    @Test fun unary_3i() {
        val mat = matrix(1, 2, 3, 4, 5, 6, 7, 8, 9)

        assertEquals(matrix( 1,  2,  3,  4,  5,  6,  7,  8,  9), +mat)
        assertEquals(matrix(-1, -2, -3, -4, -5, -6, -7, -8, -9), -mat)
    }
    @Test fun unary_4i() {
        val mat = matrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6)

        assertEquals(matrix( 1,  2,  3,  4,  5,  6,  7,  8,  9,  0,  1,  2,  3,  4,  5,  6), +mat)
        assertEquals(matrix(-1, -2, -3, -4, -5, -6, -7, -8, -9, -0, -1, -2, -3, -4, -5, -6), -mat)
    }
    @Test fun plus_2i() {
        val mat = matrix(5, 1, 1, 5)

        // plus
        assertEquals(matrix(1, 2, 3, 4), mat + matrix(-4, 1, 2, -1))

        // plus assign
        mat += matrix(-4, 1, 2, -1)
        assertEquals(matrix(1, 2, 3, 4), mat)
    }
    @Test fun plus_3i() {
        val mat = matrix(5, 1, 1, 5, 5, 4, 1, 2, 8)

        // plus
        assertEquals(matrix(1, 2, 3, 4, 5, 6, 7, 8, 9), mat + matrix(-4, 1, 2, -1, 0, 2, 6, 6, 1))

        // plus assign
        mat += matrix(-4, 1, 2, -1, 0, 2, 6, 6, 1)
        assertEquals(matrix(1, 2, 3, 4, 5, 6, 7, 8, 9), mat)
    }
    @Test fun plus_4i() {
        val mat = matrix(
            5, 1, 1, 5,
            5, 4, 1, 2,
            8, 8, 6, 4,
            2, 1, 5, 8
        )

        // plus
        assertEquals(
            matrix(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 0, 1, 2,
                3, 4, 5, 6
            ),
            mat + matrix(
                -4,  1,  2, -1,
                 0,  2,  6,  6,
                 1, -8, -5, -2,
                 1,  3,  0, -2
            )
        )

        // plus assign
        mat += matrix(-4, 1, 2, -1, 0, 2, 6, 6, 1, -8, -5, -2, 1, 3, 0, -2)
        assertEquals(matrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6), mat)
    }
    @Test fun minus_2i() {
        val mat = matrix(5, 1, 1, 5)

        // minus
        assertEquals(matrix(1, 2, 3, 4), mat - matrix(4, -1, -2, 1))

        // minus assign
        mat -= matrix(4, -1, -2, 1)
        assertEquals(matrix(1, 2, 3, 4), mat)
    }
    @Test fun minus_3i() {
        val mat = matrix(5, 1, 1, 5, 5, 4, 1, 2, 8)

        // minus
        assertEquals(matrix(1, 2, 3, 4, 5, 6, 7, 8, 9), mat - matrix(4, -1, -2, 1, 0, -2, -6, -6, -1))

        // minus assign
        mat -= matrix(4, -1, -2, 1, 0, -2, -6, -6, -1)
        assertEquals(matrix(1, 2, 3, 4, 5, 6, 7, 8, 9), mat)
    }
    @Test fun minus_4i() {
        val mat = matrix(
            5, 1, 1, 5,
            5, 4, 1, 2,
            8, 8, 6, 4,
            2, 1, 5, 8
        )

        // minus
        assertEquals(
            matrix(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 0, 1, 2,
                3, 4, 5, 6
            ),
            mat - matrix(
                 4, -1, -2,  1,
                 0, -2, -6, -6,
                -1,  8,  5,  2,
                -1, -3,  0,  2
            )
        )

        // minus assign
        mat -= matrix(4, -1, -2, 1, 0, -2, -6, -6, -1, 8, 5, 2, -1, -3, 0, 2)
        assertEquals(matrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6), mat)
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
    @Test fun times_3i() {
        val mat = matrix(5, 1, 1, 5, 8, 4, 1, 2, 6)

        // times
        assertEquals(matrix(10, 2, 2, 10, 16, 8, 2, 4, 12), mat * 2)
        assertEquals(matrix(10, 2, 2, 10, 16, 8, 2, 4, 12), 2 * mat)

        // times assign
        mat *= 2
        assertEquals(matrix(10, 2, 2, 10, 16, 8, 2, 4, 12), mat)
    }
    @Test fun times_4i() {
        val mat = matrix(5, 1, 1, 5, 8, 4, 1, 2, 6, 5, 6, 1, 4, 2, 8, 9)

        // times
        assertEquals(matrix(10, 2, 2, 10, 16, 8, 2, 4, 12, 10, 12, 2, 8, 4, 16, 18), mat * 2)
        assertEquals(matrix(10, 2, 2, 10, 16, 8, 2, 4, 12, 10, 12, 2, 8, 4, 16, 18), 2 * mat)

        // times assign
        mat *= 2
        assertEquals(matrix(10, 2, 2, 10, 16, 8, 2, 4, 12, 10, 12, 2, 8, 4, 16, 18), mat)
    }
    @Test fun div_2i() {
        val mat = matrix(10, 2, 2, 10)

        // div
        assertEquals(matrix(5, 1, 1, 5), mat / 2)

        // div assign
        mat /= 2
        assertEquals(matrix(5, 1, 1, 5), mat)
    }
    @Test fun div_3i() {
        val mat = matrix(10, 2, 2, 10, 16, 8, 2, 4, 12)

        // div
        assertEquals(matrix(5, 1, 1, 5, 8, 4, 1, 2, 6), mat / 2)

        // div assign
        mat /= 2
        assertEquals(matrix(5, 1, 1, 5, 8, 4, 1, 2, 6), mat)
    }
    @Test fun div_4i() {
        val mat = matrix(10, 2, 2, 10, 16, 8, 2, 4, 12, 10, 12, 2, 8, 4, 16, 18)

        // div
        assertEquals(matrix(5, 1, 1, 5, 8, 4, 1, 2, 6, 5, 6, 1, 4, 2, 8, 9), mat / 2)

        // div assign
        mat /= 2
        assertEquals(matrix(5, 1, 1, 5, 8, 4, 1, 2, 6, 5, 6, 1, 4, 2, 8, 9), mat)
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
    @Test fun times_m3i() {
        var m1 = matrix(1, 2, 3, 4, 5, 6, 7, 8, 9)
        var m2 = matrix(10, 11, 12, 13, 14, 15, 16, 17, 18)

        assertEquals(matrix(84, 90, 96, 201, 216, 231, 318, 342, 366), m1 * m2)
        assertEquals(matrix(138, 171, 204, 174, 216, 258, 210, 261, 312), m2 * m1)

        m1 *= m2
        assertEquals(matrix(84, 90, 96, 201, 216, 231, 318, 342, 366), m1)

        m1 = matrix(1, 2, 3, 4, 5, 6, 7, 8, 9)
        m2 *= m1
        assertEquals(matrix(138, 171, 204, 174, 216, 258, 210, 261, 312), m2)
    }
    @Test fun times_m4i() {
        var m1 = matrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        var m2 = matrix(17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32)

        assertEquals(matrix(250, 260, 270, 280, 618, 644, 670, 696, 986, 1028, 1070, 1112, 1354, 1412, 1470, 1528), m1 * m2)
        assertEquals(matrix(538, 612, 686, 760, 650, 740, 830, 920, 762, 868, 974, 1080, 874, 996, 1118, 1240), m2 * m1)

        m1 *= m2
        assertEquals(matrix(250, 260, 270, 280, 618, 644, 670, 696, 986, 1028, 1070, 1112, 1354, 1412, 1470, 1528), m1)

        m1 = matrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        m2 *= m1
        assertEquals(matrix(538, 612, 686, 760, 650, 740, 830, 920, 762, 868, 974, 1080, 874, 996, 1118, 1240), m2)
    }
    @Test fun times_v2i() {
        val mat = matrix(1, 2, 3, 4)
        val v = vector(4, 7)

        assertEquals(vector(18, 40), mat * v)
        assertEquals(vector(25, 36), v * mat)
    }
    @Test fun times_v3i() {
        val mat = matrix(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val v = vector(4, 7, 5)

        assertEquals(vector(33, 81, 129), mat * v)
        assertEquals(vector(67, 83, 99), v * mat)
    }
    @Test fun times_v4i() {
        val mat = matrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        val v = vector(4, 7, 5, 9)

        assertEquals(vector(69, 169, 269, 369), mat * v)
        assertEquals(vector(201, 226, 251, 276), v * mat)
    }
    
    // Tests (f)
    @Test fun native_2f() {
        val mat = matrix(1f, 2f, 3f, 4f)

        // equal
        assertEquals(true,  mat == matrix(1f, 2f, 3f, 4f))
        assertEquals(false, mat == matrix(1f, 5f, 5f, 1f))

        // get data (by columns not lines)
        assertArrayEquals(floatArrayOf(1f, 3f, 2f, 4f), mat.data, 0f)

        // get
        assertEquals(1f, mat[0,0])
        assertEquals(2f, mat[0,1])
        assertEquals(3f, mat[1,0])
        assertEquals(4f, mat[1,1])

        // lig
        assertEquals(vector(1f, 2f), mat.lig(0))
        assertEquals(vector(3f, 4f), mat.lig(1))

        // col
        assertEquals(vector(1f, 3f), mat.col(0))
        assertEquals(vector(2f, 4f), mat.col(1))

        // set
        mat[0,0] = 7f
        assertEquals(matrix(7f, 2f, 3f, 4f), mat)

        mat[0,1] = 4f
        assertEquals(matrix(7f, 4f, 3f, 4f), mat)
    }
    @Test fun native_3f() {
        val mat = matrix(
            1f, 2f, 3f,
            4f, 5f, 6f,
            7f, 8f, 9f
        )

        // equal
        assertEquals(true,  mat == matrix(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f))
        assertEquals(false, mat == matrix(1f, 5f, 5f, 1f, 3f, 5f, 1f, 7f, 6f))

        // get data (by columns not lines)
        assertArrayEquals(floatArrayOf(1f, 4f, 7f, 2f, 5f, 8f, 3f, 6f, 9f), mat.data, 0f)

        // get
        assertEquals(1f, mat[0,0])
        assertEquals(2f, mat[0,1])
        assertEquals(3f, mat[0,2])
        assertEquals(4f, mat[1,0])
        assertEquals(5f, mat[1,1])
        assertEquals(6f, mat[1,2])
        assertEquals(7f, mat[2,0])
        assertEquals(8f, mat[2,1])
        assertEquals(9f, mat[2,2])

        // lig
        assertEquals(vector(1f, 2f, 3f), mat.lig(0))
        assertEquals(vector(4f, 5f, 6f), mat.lig(1))
        assertEquals(vector(7f, 8f, 9f), mat.lig(2))

        // col
        assertEquals(vector(1f, 4f, 7f), mat.col(0))
        assertEquals(vector(2f, 5f, 8f), mat.col(1))
        assertEquals(vector(3f, 6f, 9f), mat.col(2))

        // set
        mat[0,0] = 7f
        assertEquals(matrix(7f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f), mat)

        mat[0,1] = 4f
        assertEquals(matrix(7f, 4f, 3f, 4f, 5f, 6f, 7f, 8f, 9f), mat)

        mat[2,1] = 1f
        assertEquals(matrix(7f, 4f, 3f, 4f, 5f, 6f, 7f, 1f, 9f), mat)
    }
    @Test fun native_4f() {
        val mat = matrix(
             1f,  2f,  3f,  4f,
             5f,  6f,  7f,  8f,
             9f, 10f, 11f, 12f,
            13f, 14f, 15f, 16f
        )

        // equal
        assertEquals(true,  mat == matrix(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f, 12f, 13f, 14f, 15f, 16f))
        assertEquals(false, mat == matrix(1f, 5f, 5f, 1f, 3f, 5f, 1f, 7f, 6f, 7f, 5f, 2f, 1f, 4f, 5f, 8f))

        // get data (by columns not lines)
        assertArrayEquals(floatArrayOf(1f, 5f, 9f, 13f, 2f, 6f, 10f, 14f, 3f, 7f, 11f, 15f, 4f, 8f, 12f, 16f), mat.data, 0f)

        // get
        assertEquals( 1f, mat[0,0])
        assertEquals( 2f, mat[0,1])
        assertEquals( 3f, mat[0,2])
        assertEquals( 4f, mat[0,3])
        assertEquals( 5f, mat[1,0])
        assertEquals( 6f, mat[1,1])
        assertEquals( 7f, mat[1,2])
        assertEquals( 8f, mat[1,3])
        assertEquals( 9f, mat[2,0])
        assertEquals(10f, mat[2,1])
        assertEquals(11f, mat[2,2])
        assertEquals(12f, mat[2,3])
        assertEquals(13f, mat[3,0])
        assertEquals(14f, mat[3,1])
        assertEquals(15f, mat[3,2])
        assertEquals(16f, mat[3,3])

        // lig
        assertEquals(vector( 1f,  2f,  3f,  4f), mat.lig(0))
        assertEquals(vector( 5f,  6f,  7f,  8f), mat.lig(1))
        assertEquals(vector( 9f, 10f, 11f, 12f), mat.lig(2))
        assertEquals(vector(13f, 14f, 15f, 16f), mat.lig(3))

        // col
        assertEquals(vector(1f, 5f,  9f, 13f), mat.col(0))
        assertEquals(vector(2f, 6f, 10f, 14f), mat.col(1))
        assertEquals(vector(3f, 7f, 11f, 15f), mat.col(2))
        assertEquals(vector(4f, 8f, 12f, 16f), mat.col(3))

        // set
        mat[0,0] = 7f
        assertEquals(matrix(7f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f, 12f, 13f, 14f, 15f, 16f), mat)

        mat[0,1] = 4f
        assertEquals(matrix(7f, 4f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f, 12f, 13f, 14f, 15f, 16f), mat)

        mat[2,1] = 1f
        assertEquals(matrix(7f, 4f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 1f, 11f, 12f, 13f, 14f, 15f, 16f), mat)

        mat[3,3] = 8f
        assertEquals(matrix(7f, 4f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 1f, 11f, 12f, 13f, 14f, 15f, 8f), mat)
    }
    @Test fun coord_gen_2f() {
        val matl = Mat2f { l, _ -> l.toFloat() }
        assertEquals(matrix(0f, 0f, 1f, 1f), matl)

        val matc = Mat2f { _, c -> c.toFloat() }
        assertEquals(matrix(0f, 1f, 0f, 1f), matc)
    }
    @Test fun coord_gen_3f() {
        val matl = Mat3f { l, _ -> l.toFloat() }
        assertEquals(matrix(0f, 0f, 0f, 1f, 1f, 1f, 2f, 2f, 2f), matl)

        val matc = Mat3f { _, c -> c.toFloat() }
        assertEquals(matrix(0f, 1f, 2f, 0f, 1f, 2f, 0f, 1f, 2f), matc)
    }
    @Test fun coord_gen_4f() {
        val matl = Mat4f { l, _ -> l.toFloat() }
        assertEquals(matrix(0f, 0f, 0f, 0f, 1f, 1f, 1f, 1f, 2f, 2f, 2f, 2f, 3f, 3f, 3f, 3f), matl)

        val matc = Mat4f { _, c -> c.toFloat() }
        assertEquals(matrix(0f, 1f, 2f, 3f, 0f, 1f, 2f, 3f, 0f, 1f, 2f, 3f, 0f, 1f, 2f, 3f), matc)
    }
    @Test fun unary_2f() {
        val mat = matrix(5f, 1f, 1f, 5f)

        assertEquals(matrix( 5f,  1f,  1f,  5f), +mat)
        assertEquals(matrix(-5f, -1f, -1f, -5f), -mat)
    }
    @Test fun unary_3f() {
        val mat = matrix(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f)

        assertEquals(matrix( 1f,  2f,  3f,  4f,  5f,  6f,  7f,  8f,  9f), +mat)
        assertEquals(matrix(-1f, -2f, -3f, -4f, -5f, -6f, -7f, -8f, -9f), -mat)
    }
    @Test fun unary_4f() {
        val mat = matrix(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 0f, 1f, 2f, 3f, 4f, 5f, 6f)

        assertEquals(matrix( 1f,  2f,  3f,  4f,  5f,  6f,  7f,  8f,  9f,  0f,  1f,  2f,  3f,  4f,  5f,  6f), +mat)
        assertEquals(matrix(-1f, -2f, -3f, -4f, -5f, -6f, -7f, -8f, -9f, -0f, -1f, -2f, -3f, -4f, -5f, -6f), -mat)
    }
    @Test fun plus_2f() {
        val mat = matrix(5f, 1f, 1f, 5f)

        // plus
        assertEquals(matrix(1f, 2f, 3f, 4f), mat + matrix(-4f, 1f, 2f, -1f))

        // plus assign
        mat += matrix(-4f, 1f, 2f, -1f)
        assertEquals(matrix(1f, 2f, 3f, 4f), mat)
    }
    @Test fun plus_3f() {
        val mat = matrix(5f, 1f, 1f, 5f, 5f, 4f, 1f, 2f, 8f)

        // plus
        assertEquals(matrix(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f), mat + matrix(-4f, 1f, 2f, -1f, 0f, 2f, 6f, 6f, 1f))

        // plus assign
        mat += matrix(-4f, 1f, 2f, -1f, 0f, 2f, 6f, 6f, 1f)
        assertEquals(matrix(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f), mat)
    }
    @Test fun plus_4f() {
        val mat = matrix(
            5f, 1f, 1f, 5f,
            5f, 4f, 1f, 2f,
            8f, 8f, 6f, 4f,
            2f, 1f, 5f, 8f
        )

        // plus
        assertEquals(
            matrix(
                1f, 2f, 3f, 4f,
                5f, 6f, 7f, 8f,
                9f, 0f, 1f, 2f,
                3f, 4f, 5f, 6f
            ),
            mat + matrix(
                -4f,  1f,  2f, -1f,
                 0f,  2f,  6f,  6f,
                 1f, -8f, -5f, -2f,
                 1f,  3f,  0f, -2f
            )
        )

        // plus assign
        mat += matrix(-4f, 1f, 2f, -1f, 0f, 2f, 6f, 6f, 1f, -8f, -5f, -2f, 1f, 3f, 0f, -2f)
        assertEquals(matrix(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 0f, 1f, 2f, 3f, 4f, 5f, 6f), mat)
    }
    @Test fun minus_2f() {
        val mat = matrix(5f, 1f, 1f, 5f)

        // minus
        assertEquals(matrix(1f, 2f, 3f, 4f), mat - matrix(4f, -1f, -2f, 1f))

        // minus assign
        mat -= matrix(4f, -1f, -2f, 1f)
        assertEquals(matrix(1f, 2f, 3f, 4f), mat)
    }
    @Test fun minus_3f() {
        val mat = matrix(5f, 1f, 1f, 5f, 5f, 4f, 1f, 2f, 8f)

        // minus
        assertEquals(matrix(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f), mat - matrix(4f, -1f, -2f, 1f, 0f, -2f, -6f, -6f, -1f))

        // minus assign
        mat -= matrix(4f, -1f, -2f, 1f, 0f, -2f, -6f, -6f, -1f)
        assertEquals(matrix(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f), mat)
    }
    @Test fun minus_4f() {
        val mat = matrix(
            5f, 1f, 1f, 5f,
            5f, 4f, 1f, 2f,
            8f, 8f, 6f, 4f,
            2f, 1f, 5f, 8f
        )

        // minus
        assertEquals(
            matrix(
                1f, 2f, 3f, 4f,
                5f, 6f, 7f, 8f,
                9f, 0f, 1f, 2f,
                3f, 4f, 5f, 6f
            ),
            mat - matrix(
                 4f, -1f, -2f,  1f,
                 0f, -2f, -6f, -6f,
                -1f,  8f,  5f,  2f,
                -1f, -3f,  0f,  2f
            )
        )

        // minus assign
        mat -= matrix(4f, -1f, -2f, 1f, 0f, -2f, -6f, -6f, -1f, 8f, 5f, 2f, -1f, -3f, 0f, 2f)
        assertEquals(matrix(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 0f, 1f, 2f, 3f, 4f, 5f, 6f), mat)
    }
    @Test fun times_2f() {
        val mat = matrix(5f, 1f, 1f, 5f)

        // times
        assertEquals(matrix(10f, 2f, 2f, 10f), mat * 2f)
        assertEquals(matrix(10f, 2f, 2f, 10f), 2f * mat)

        // times assign
        mat *= 2f
        assertEquals(matrix(10f, 2f, 2f, 10f), mat)
    }
    @Test fun times_3f() {
        val mat = matrix(5f, 1f, 1f, 5f, 8f, 4f, 1f, 2f, 6f)

        // times
        assertEquals(matrix(10f, 2f, 2f, 10f, 16f, 8f, 2f, 4f, 12f), mat * 2f)
        assertEquals(matrix(10f, 2f, 2f, 10f, 16f, 8f, 2f, 4f, 12f), 2f * mat)

        // times assign
        mat *= 2f
        assertEquals(matrix(10f, 2f, 2f, 10f, 16f, 8f, 2f, 4f, 12f), mat)
    }
    @Test fun times_4f() {
        val mat = matrix(5f, 1f, 1f, 5f, 8f, 4f, 1f, 2f, 6f, 5f, 6f, 1f, 4f, 2f, 8f, 9f)

        // times
        assertEquals(matrix(10f, 2f, 2f, 10f, 16f, 8f, 2f, 4f, 12f, 10f, 12f, 2f, 8f, 4f, 16f, 18f), mat * 2f)
        assertEquals(matrix(10f, 2f, 2f, 10f, 16f, 8f, 2f, 4f, 12f, 10f, 12f, 2f, 8f, 4f, 16f, 18f), 2f * mat)

        // times assign
        mat *= 2f
        assertEquals(matrix(10f, 2f, 2f, 10f, 16f, 8f, 2f, 4f, 12f, 10f, 12f, 2f, 8f, 4f, 16f, 18f), mat)
    }
    @Test fun div_2f() {
        val mat = matrix(10f, 2f, 2f, 10f)

        // div
        assertEquals(matrix(5f, 1f, 1f, 5f), mat / 2f)

        // div assign
        mat /= 2f
        assertEquals(matrix(5f, 1f, 1f, 5f), mat)
    }
    @Test fun div_3f() {
        val mat = matrix(10f, 2f, 2f, 10f, 16f, 8f, 2f, 4f, 12f)

        // div
        assertEquals(matrix(5f, 1f, 1f, 5f, 8f, 4f, 1f, 2f, 6f), mat / 2f)

        // div assign
        mat /= 2f
        assertEquals(matrix(5f, 1f, 1f, 5f, 8f, 4f, 1f, 2f, 6f), mat)
    }
    @Test fun div_4f() {
        val mat = matrix(10f, 2f, 2f, 10f, 16f, 8f, 2f, 4f, 12f, 10f, 12f, 2f, 8f, 4f, 16f, 18f)

        // div
        assertEquals(matrix(5f, 1f, 1f, 5f, 8f, 4f, 1f, 2f, 6f, 5f, 6f, 1f, 4f, 2f, 8f, 9f), mat / 2f)

        // div assign
        mat /= 2f
        assertEquals(matrix(5f, 1f, 1f, 5f, 8f, 4f, 1f, 2f, 6f, 5f, 6f, 1f, 4f, 2f, 8f, 9f), mat)
    }
    @Test fun times_m2f() {
        var m1 = matrix(1f, 2f, 3f, 4f)
        var m2 = matrix(5f, 6f, 7f, 8f)

        assertEquals(matrix(19f, 22f, 43f, 50f), m1 * m2)
        assertEquals(matrix(23f, 34f, 31f, 46f), m2 * m1)

        m1 *= m2
        assertEquals(matrix(19f, 22f, 43f, 50f), m1)

        m1 = matrix(1f, 2f, 3f, 4f)
        m2 *= m1
        assertEquals(matrix(23f, 34f, 31f, 46f), m2)
    }
    @Test fun times_m3f() {
        var m1 = matrix(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f)
        var m2 = matrix(10f, 11f, 12f, 13f, 14f, 15f, 16f, 17f, 18f)

        assertEquals(matrix(84f, 90f, 96f, 201f, 216f, 231f, 318f, 342f, 366f), m1 * m2)
        assertEquals(matrix(138f, 171f, 204f, 174f, 216f, 258f, 210f, 261f, 312f), m2 * m1)

        m1 *= m2
        assertEquals(matrix(84f, 90f, 96f, 201f, 216f, 231f, 318f, 342f, 366f), m1)

        m1 = matrix(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f)
        m2 *= m1
        assertEquals(matrix(138f, 171f, 204f, 174f, 216f, 258f, 210f, 261f, 312f), m2)
    }
    @Test fun times_m4f() {
        var m1 = matrix(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f, 12f, 13f, 14f, 15f, 16f)
        var m2 = matrix(17f, 18f, 19f, 20f, 21f, 22f, 23f, 24f, 25f, 26f, 27f, 28f, 29f, 30f, 31f, 32f)

        assertEquals(matrix(250f, 260f, 270f, 280f, 618f, 644f, 670f, 696f, 986f, 1028f, 1070f, 1112f, 1354f, 1412f, 1470f, 1528f), m1 * m2)
        assertEquals(matrix(538f, 612f, 686f, 760f, 650f, 740f, 830f, 920f, 762f, 868f, 974f, 1080f, 874f, 996f, 1118f, 1240f), m2 * m1)

        m1 *= m2
        assertEquals(matrix(250f, 260f, 270f, 280f, 618f, 644f, 670f, 696f, 986f, 1028f, 1070f, 1112f, 1354f, 1412f, 1470f, 1528f), m1)

        m1 = matrix(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f, 12f, 13f, 14f, 15f, 16f)
        m2 *= m1
        assertEquals(matrix(538f, 612f, 686f, 760f, 650f, 740f, 830f, 920f, 762f, 868f, 974f, 1080f, 874f, 996f, 1118f, 1240f), m2)
    }
    @Test fun times_v2f() {
        val mat = matrix(1f, 2f, 3f, 4f)
        val v = vector(4f, 7f)

        assertEquals(vector(18f, 40f), mat * v)
        assertEquals(vector(25f, 36f), v * mat)
    }
    @Test fun times_v3f() {
        val mat = matrix(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f)
        val v = vector(4f, 7f, 5f)

        assertEquals(vector(33f, 81f, 129f), mat * v)
        assertEquals(vector(67f, 83f, 99f), v * mat)
    }
    @Test fun times_v4f() {
        val mat = matrix(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f, 12f, 13f, 14f, 15f, 16f)
        val v = vector(4f, 7f, 5f, 9f)

        assertEquals(vector(69f, 169f, 269f, 369f), mat * v)
        assertEquals(vector(201f, 226f, 251f, 276f), v * mat)
    }
}