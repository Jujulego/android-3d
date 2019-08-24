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
        assertSame(arr[0], arr[0])

        val v = vector(5, 8)

        arr[0] = v
        assertSame(v, arr[0])
    }
    @Test(expected = ArrayIndexOutOfBoundsException::class)
    fun get_out_of_bounds_2i() {
        val arr = VectorArray2i(1)
        arr[8]
    }
    @Test fun interator_2i() {
        val arr = arrayOf(vector(0, 0), vector(1, 1))

        val it = arr.listIterator()

        assertFalse(it.hasPrevious())
        assertTrue(it.hasNext())

        assertEquals(vector(0, 0), it.next())

        assertTrue(it.hasPrevious())
        assertFalse(it.hasNext())

        assertEquals(vector(0, 0), it.previous())

        assertFalse(it.hasPrevious())
        assertTrue(it.hasNext())
    }
    @Test fun add_2i() {
        val arr = VectorArray2i()
        val v = vector(8, 4)

        arr.add(v)
        assertEquals(1, arr.size)
        assertSame(v, arr[0])
    }
    @Test fun add_index_2i() {
        val arr = arrayOf(vector(0, 0), vector(2, 2))

        val v = vector(1, 1)
        arr.add(1, v)

        assertEquals(3, arr.size)
        assertSame(v, arr[1])
    }
    @Test fun size_2i() {
        assertEquals(0, VectorArray2i().size)
        assertEquals(5, VectorArray2i(5).size)
    }
    @Test fun contains_2i() {
        val arr = arrayOf(vector(0, 0), vector(1, 1))

        assertTrue(arr.contains(vector(1, 1)))
        assertFalse(arr.contains(vector(2, 2)))
    }
    @Test fun containsAll_2i() {
        val arr = arrayOf(vector(0, 0), vector(1, 1))

        assertTrue(arr.containsAll(listOf(vector(1, 1))))
        assertFalse(arr.containsAll(listOf(vector(1, 1), vector(2, 2))))
    }
    @Test fun remove_existing_2i() {
        val arr = arrayOf(vector(0, 0), vector(1, 1), vector(2, 2))

        assertTrue(arr.remove(vector(1, 1)))

        assertEquals(arr.size, 2)
        assertEquals(arr[0], vector(0, 0))
        assertEquals(arr[1], vector(2, 2))
    }
    @Test fun remove_absent_2i() {
        val arr = arrayOf(vector(0, 0), vector(2, 2))

        assertFalse(arr.remove(vector(1, 1)))

        assertEquals(arr.size, 2)
        assertEquals(arr[0], vector(0, 0))
        assertEquals(arr[1], vector(2, 2))
    }
    @Test fun clear_2i() {
        val arr = arrayOf(vector(0, 0), vector(1, 1), vector(2, 2))
        arr.clear()

        assertEquals(arr.size, 0)
    }
}