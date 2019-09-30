package net.capellari.julien.threed

import net.capellari.julien.threed.gpu.Program
import net.capellari.julien.threed.gpu.Uniformable

open class Program {
    // Attributes
    protected val program = Program()
    private var uniforms = mutableListOf<Uniform<*>>()

    // Methods
    fun use() {
        program.use()

        syncUniforms()
    }

    // - events
    open fun onUpdateScreen(width: Float, height: Float) {}

    // - uniforms
    fun<T: Uniformable> uniform(default: T) = UniformLoader { default }
    fun<T: Uniformable> uniform(default: () -> T) = UniformLoader(default)
    fun<T: Uniformable> uniform(name: String, default: T) = buildUniform(name) { default }
    fun<T: Uniformable> uniform(name: String, default: () -> T) = buildUniform(name, default)

    internal fun<T: Uniformable> buildUniform(name: String, default: () -> T): UniformProperty<T> {
        val uniform = Uniform(name, default)

        uniforms.add(uniform)
        return UniformProperty(uniform)
    }

    protected fun linkUniforms() {
        uniforms.forEach { it.linkTo(program) }
    }

    private fun syncUniforms() {
        uniforms.forEach { it.sync() }
    }
}

