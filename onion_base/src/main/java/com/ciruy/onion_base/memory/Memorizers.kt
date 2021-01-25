package com.ciruy.onion_base.memory

import com.ciruy.b.heimerdinger.onion.changeFrom
import com.ciruy.b.heimerdinger.onion.changeTo
import com.ciruy.onion_base.util.LogUtil
import java.util.concurrent.ConcurrentHashMap

abstract class BaseMemorizers<T, U>(private val applicable: (T) -> U) {
    private val cache = ConcurrentHashMap<T, U>()
    fun computeIfAbsentOrigin(t: T) = if (cache.containsKey(t)) {
        LogUtil.e("cache contains $t")
        cache[t]
    } else {
        LogUtil.e("cache not contains $t")
        val u = applicable.invoke(t)
        cache[t] = u
        u
    }

    fun forgetOrigin(t: T) = cache.remove(t)
}


class StrongMemorizers<T, U>

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

    fun computeIfAbsent(t: T) = computeIfAbsentOrigin(softReference<T?>().invoke(t))?.get()
    fun forget(t: T) = computeIfAbsentOrigin(softReference<T?>().invoke(t))?.get()
}

class WeakMemorizers<T, U>
