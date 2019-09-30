package net.capellari.julien.threed

import net.capellari.julien.threed.gpu.Uniformable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class UniformProperty<T: Uniformable>(val uniform: Uniform<T>): ReadWriteProperty<Program,T> {
    // Methods
    override fun getValue(thisRef: Program, property: KProperty<*>) = uniform.value
    override fun setValue(thisRef: Program, property: KProperty<*>, value: T) {
        uniform.value = value
    }
}

class UniformLoader<T: Uniformable>(val default: () -> T) {
    // Operators
    operator fun provideDelegate(thisRef: Program, property: KProperty<*>): ReadWriteProperty<Program,T> {
        return thisRef.buildUniform(property.name, default)
    }
}