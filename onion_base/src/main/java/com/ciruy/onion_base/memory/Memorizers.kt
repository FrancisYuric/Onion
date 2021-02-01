package com.ciruy.onion_base.memory

import com.ciruy.b.heimerdinger.onion.changeFrom
import com.ciruy.b.heimerdinger.onion.changeTo
import com.ciruy.onion_base.rx.createIfNull
import java.util.concurrent.ConcurrentHashMap

abstract class BaseMemorizers<T, U>(private val applicable: (T) -> U) {
    private val cache = ConcurrentHashMap<T, U>()
    fun computeIfAbsentOrigin(t: T): U = cache.createIfNull(t) { applicable.invoke(t) }
//    {
//        cache[t] = cache[t] ?: applicable.invoke(t)
//        return cache[t]!!
//    }

    fun forgetOrigin(t: T) = cache.remove(t)
}


class SoftMemorizers<T, U>(applicable: (ComparableSoftReference<T?>) -> ComparableSoftReference<U>)
    : BaseMemorizers<ComparableSoftReference<T?>, ComparableSoftReference<U>>(applicable) {
    companion object {
        fun <T, U> applicable(applicable: (T?) -> U) =
                SoftMemorizers(applicable
                        .changeFrom<ComparableSoftReference<T?>, T?, U> { unwrapReference<T?>().invoke(it) }
                        .changeTo { softReference<U>().invoke(it) })

        private fun <U> softReference(): (u: U) -> ComparableSoftReference<U> = {
            ComparableSoftReference(it)
        }

        private fun <U> unwrapReference(): (ComparableSoftReference<U?>) -> U? = ComparableSoftReference<U?>::get
    }

    fun computeIfAbsent(t: T) = computeIfAbsentOrigin(softReference<T?>().invoke(t)).get()
    fun forget(t: T) = computeIfAbsentOrigin(softReference<T?>().invoke(t)).get()
}

class WeakMemorizers<T, U>(applicable: (ComparableWeakReference<T?>) -> ComparableWeakReference<U>)
    : BaseMemorizers<ComparableWeakReference<T?>, ComparableWeakReference<U>>(applicable) {
    companion object {
        fun <T, U> applicable(applicable: (T?) -> U) =
                WeakMemorizers(applicable
                        .changeFrom<ComparableWeakReference<T?>, T?, U> { unwrapReference<T?>().invoke(it) }
                        .changeTo { weakReference<U>().invoke(it) })


        private fun <U> weakReference(): (u: U) -> ComparableWeakReference<U> = {
            ComparableWeakReference(it)
        }

        private fun <U> unwrapReference(): (ComparableWeakReference<U?>) -> U? = ComparableWeakReference<U?>::get
    }
}
