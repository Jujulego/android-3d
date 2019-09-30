package net.capellari.julien.threed

import net.capellari.julien.threed.gpu.*
import net.capellari.julien.threed.gpu.Program
import org.intellij.lang.annotations.Language

open class Program {
    // Attributes
    protected val program = Program()

    private var uniforms = mutableListOf<Uniform<*>>()
    private var vertexAttributes = mutableListOf<VertexAttribute>()

    // Methods
    fun use() {
        program.use()

        syncUniforms()
    }

    // - events
    open fun onUpdateScreen(width: Float, height: Float) {}

    // - shader
    fun vertexShader(@Language("GLSL") source: String)  = program.addShader(ShaderType.VERTEX, source)
    fun geometryShader(@Language("GLSL") source: String) = program.addShader(ShaderType.GEOMETRY, source)
    fun fragmentShader(@Language("GLSL") source: String) = program.addShader(ShaderType.FRAGMENT, source)

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

    // - vertex attributes
    inline fun<reified T: Any> vertexAttribute() = VertexAttributeLoader(gpuType<T>(), gpuSize<T>())
    inline fun<reified T: Any> vertexAttribute(name: String) = buildVertexAttribute(name, gpuType<T>(), gpuSize<T>())

    fun buildVertexAttribute(name: String, type: Type, size: Int): VertexAttributeProperty {
        val vattr = VertexAttribute(name, type, size)

        vertexAttributes.add(vattr)
        return VertexAttributeProperty(vattr)
    }

    protected fun linkVertexAttributes() {
        vertexAttributes.forEach { program.addVertexAttribute(it) }
    }
}

