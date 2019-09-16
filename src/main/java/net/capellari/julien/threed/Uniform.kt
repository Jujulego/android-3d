package net.capellari.julien.threed

import net.capellari.julien.threed.gpu.Program
import net.capellari.julien.threed.gpu.Uniform
import net.capellari.julien.threed.gpu.Uniformable

class Uniform<T: Uniformable>(val name: String, default: () -> T) {
    // Attributes
    var uniform: Uniform? = null
    var changed = true
        private set

    // Properties
    var value: T = default()
        set(value) {
            field = value
            changed = true
        }

    // Methods
    fun linkTo(program: Program) {
        uniform = program.addUniform(name)
    }

    fun sync() {
        if (changed) {
            uniform?.let {
                it.setValue(value)
                changed = false
            }
        }
    }
}