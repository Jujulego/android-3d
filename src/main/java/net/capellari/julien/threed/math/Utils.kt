package net.capellari.julien.threed.math

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// Fonctions
inline fun <reified R: Number, reified T: Coords<R,*>> coordX() = CoordDelegate<R,T>(0)
inline fun <reified R: Number, reified T: Coords<R,*>> coordY() = CoordDelegate<R,T>(1)

// Classe
class CoordDelegate<R: Number, in T: Coords<R,*>>(val i: Int): ReadWriteProperty<T,R> {
    // Méthodes
    override fun getValue(thisRef: T, property: KProperty<*>): R {
        return thisRef[i]
    }

    override fun setValue(thisRef: T, property: KProperty<*>, value: R) {
        thisRef[i] = value
    }
}