package net.capellari.julien.threed

import net.capellari.julien.threed.gpu.Uniform
import net.capellari.julien.threed.gpu.Uniformable

class Uniform<T: Uniformable> internal constructor(val uniform: Uniform, default: T) {
    // Attributes
    var changed = true
        private set

    // Properties
    var value: T = default
        set(value) {
            field = value
            changed = true
        }

    // Methods
    fun sync() {
        if (changed) {
            uniform.setValue(value)
            changed = false
        }
    }
}