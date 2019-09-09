package net.capellari.julien.threed.gpu

import net.capellari.julien.threed.jni.JNIClass
import org.intellij.lang.annotations.Language

class Program: JNIClass(create()) {
    // Companion
    companion object {
        @JvmStatic private external fun create(): Long
    }

    // Methods
    // - native
    external fun addShader(shader: Shader)
    external fun addVertexAttribute(attribute: VertexAttribute)

    fun addUniform(name: String) = Uniform(naddUniform(name))
    private external fun naddUniform(name: String): Long

    external fun compile()
    external fun use()
    external fun destroy()

    // - utils
    fun addShader(type: ShaderType, @Language("GLSL") source: String) {
        val shader = Shader(type)

        shader.source = source
        addShader(shader)
    }
}