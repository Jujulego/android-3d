package net.capellari.julien.threed.gpu

import net.capellari.julien.threed.jni.JNIClass
import org.intellij.lang.annotations.Language

sealed class Shader(type: ShaderType) : JNIClass(create(type.gl)) {
    // Companion
    companion object {
        // Statics
        @JvmStatic private external fun create(type: Int): Long
    }

    // Methods
    external fun setSource(@Language("GLSL") source: String)
    external fun compile()
}

class VertexShader: Shader(ShaderType.VERTEX)
class GeometryShader: Shader(ShaderType.GEOMETRY)
class FragmentShader: Shader(ShaderType.FRAGMENT)
