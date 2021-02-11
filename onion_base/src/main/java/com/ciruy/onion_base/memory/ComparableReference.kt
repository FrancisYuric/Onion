package com.ciruy.onion_base.memory

import java.lang.ref.SoftReference
import java.lang.ref.WeakReference

class ComparableSoftReference<T>(private val referent: T) : SoftReference<T>(referent) {

    override fun hashCode(): Int = get()?.hashCode()?:-1

    override fun toString(): String = referent.toString()

    override fun equals(other: Any?): Boolean = other is ComparableSoftReference<*> && get() == other.get()
}

class ComparableWeakReference<T>(private val referent: T) : WeakReference<T>(referent) {
    override fun hashCode(): Int = get()?.hashCode()?:-1

    override fun toString(): String = referent.toString()

    override fun equals(other: Any?): Boolean = other is ComparableWeakReference<*> && get() == other.get()
}
