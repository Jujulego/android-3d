package net.capellari.julien.threed

import android.content.Context
import android.graphics.Color
import android.graphics.Color.*
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import net.capellari.julien.threed.math.Coords
import net.capellari.julien.threed.math.Three
import net.capellari.julien.threed.math.coords.RGB

class Color3 : RGB {
    // Constructeurs
    constructor(): super()
    constructor(r: Float, g: Float, b: Float): super(r, g, b)

    constructor(context: Context, @ColorRes color: Int): this(context.getColor(color))
    constructor(@ColorInt color: Int): this(red(color) / 255f, green(color) / 255f, blue(color) / 255f)

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(color: Color): this(color.toArgb())

    // Méthodes
    override fun newCoords() = Color3()
}