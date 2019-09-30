package net.capellari.julien.threed

import net.capellari.julien.threed.gpu.Type
import net.capellari.julien.threed.gpu.VertexAttribute
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class VertexAttributeProperty(val value: VertexAttribute): ReadOnlyProperty<Program,VertexAttribute> {
    // Methods
    override fun getValue(thisRef: Program, property: KProperty<*>) = value
}

class VertexAttributeLoader(val type: Type, val size: Int) {
    // Operators
    operator fun provideDelegate(thisRef: Program, property: KProperty<*>): ReadOnlyProperty<Program,VertexAttribute> {
        return thisRef.buildVertexAttribute(property.name, type, size)
    }
}