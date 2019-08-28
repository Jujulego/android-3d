package net.capellari.julien.threed.gpu

import net.capellari.julien.threed.jni.JNIClass

sealed class Shader(type: ShaderType) : JNIClass(create(type.gl)) {
    // Companion
    companion object {
        // Statics
        @JvmStatic private external fun create(type: Int): Long
    }

    // Properties
    var source: String
        external get
        external set

    // Methods
    external fun compile()
    external fun destroy()
}

class VertexShader: Shader(ShaderType.VERTEX)
class GeometryShader: Shader(ShaderType.GEOMETRY)
class FragmentShader: Shader(ShaderType.FRAGMENT)
