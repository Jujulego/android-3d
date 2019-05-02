package net.capellari.julien.threed.math

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// Fonctions
inline fun <R: Number, reified T: Coord<R,*>> coordX() = CoordDelegate<R,T>(0)
inline fun <R: Number, reified T: Coord<R,*>> coordY() = CoordDelegate<R,T>(1)
inline fun <R: Number, reified T: Coord<R,*>> coordZ() = CoordDelegate<R,T>(2)
inline fun <R: Number, reified T: Coord<R,*>> coordA() = CoordDelegate<R,T>(3)

// Classe
class CoordDelegate<R: Number, in T: Coord<R,*>>(val i: Int): ReadWriteProperty<T,R> {
    // Méthodes
    override fun getValue(thisRef: T, property: KProperty<*>): R {
        return thisRef[i]
    }

    override fun setValue(thisRef: T, property: KProperty<*>, value: R) {
        thisRef[i] = value
    }
}