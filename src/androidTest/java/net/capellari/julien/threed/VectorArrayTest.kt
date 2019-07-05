package net.capellari.julien.threed

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VectorArrayTest {
    // Tests (i)
    @Test fun get_set_2i() {
        val arr = VectorArray2i(1)
        assertEquals(vector(0, 0), arr[0])

        arr[0] = vector(5, 8)
        assertEquals(vector(5, 8), arr[0])
    }
    @Test(expected = ArrayIndexOutOfBoundsException::class)
    fun get_out_of_bounds_2i() {
        val arr = VectorArray2i(1)
        arr[8]
    }

    @Test fun add_2i() {
        val arr = VectorArray2i()

        arr.add(vector(8, 4))
        assertEquals(1, arr.size)
        assertEquals(vector(8, 4), arr[0])
    }

    @Test fun size_2i() {
        assertEquals(0, VectorArray2i().size)
        assertEquals(5, VectorArray2i(5).size)
    }
}