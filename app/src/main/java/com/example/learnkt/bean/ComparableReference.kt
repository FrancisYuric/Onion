package com.example.learnkt.bean

import java.lang.ref.SoftReference

class ComparableSoftReference<T> constructor(referent: T) : SoftReference<T>(referent) {

    override fun hashCode(): Int = get()!!.hashCode()

    override fun equals(other: Any?): Boolean = if (other is ComparableSoftReference<*>) get() == other.get() else false
}
