package net.capellari.julien.threed

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ThreeDTest {
    @Test fun scale_4i() {
        val mat = scale(2, 2, 2)
        val pt = point(1, 1, 1)

        Assert.assertEquals(point(2, 2, 2), pt * mat)
    }
    @Test fun translate_4i() {
        val mat = translate(1, 2, 3)
        val pt = point(1, 1, 1)

        Assert.assertEquals(point(2, 3, 4), pt * mat)
    }
    @Test fun rotate_4i() {
        val pt = point(1, 1, 1)

        // clkw = clock-wise    cclkw = counter clock-wise
        Assert.assertEquals(point(1, -1, 1), pt * rotate(-90.0, 1, 0, 0)) // 90 clkw x
        Assert.assertEquals(point(1, 1, -1), pt * rotate(-90.0, 0, 1, 0)) // 90 clkw y
        Assert.assertEquals(point(1, -1, 1), pt * rotate(-90.0, 0, 0, 1)) // 90 clkw z

        Assert.assertEquals(point(1, 1, -1), pt * rotate(90.0, 1, 0, 0)) // 90 cclkw x
        Assert.assertEquals(point(-1, 1, 1), pt * rotate(90.0, 0, 1, 0)) // 90 cclkw y
        Assert.assertEquals(point(-1, 1, 1), pt * rotate(90.0, 0, 0, 1)) // 90 cclkw z
    }

    @Test fun scale_4f() {
        val mat = scale(2f, 2f, 2f)
        val pt = point(1f, 1f, 1f)

        Assert.assertEquals(point(2f, 2f, 2f), pt * mat)
    }
    @Test fun translate_4f() {
        val mat = translate(1f, 2f, 3f)
        val pt = point(1f, 1f, 1f)

        Assert.assertEquals(point(2f, 3f, 4f), pt * mat)
    }
    @Test fun rotate_4f() {
        val pt = point(1f, 1f, 1f)

        // clkw = clock-wise    cclkw = counter clock-wise
        Assert.assertEquals(point(1f, -1f, 1f), pt * rotate(-90.0, 1f, 0f, 0f)) // 90 clkw x
        Assert.assertEquals(point(1f, 1f, -1f), pt * rotate(-90.0, 0f, 1f, 0f)) // 90 clkw y
        Assert.assertEquals(point(1f, -1f, 1f), pt * rotate(-90.0, 0f, 0f, 1f)) // 90 clkw z

        Assert.assertEquals(point(1f, 1f, -1f), pt * rotate(90.0, 1f, 0f, 0f)) // 90 cclkw x
        Assert.assertEquals(point(-1f, 1f, 1f), pt * rotate(90.0, 0f, 1f, 0f)) // 90 cclkw y
        Assert.assertEquals(point(-1f, 1f, 1f), pt * rotate(90.0, 0f, 0f, 1f)) // 90 cclkw z
    }
}