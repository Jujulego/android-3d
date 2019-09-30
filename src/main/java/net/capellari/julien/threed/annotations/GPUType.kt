package net.capellari.julien.threed.annotations

import net.capellari.julien.threed.gpu.Type
import kotlin.annotation.Target

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class GPUType(val type: Type, val size: Int)