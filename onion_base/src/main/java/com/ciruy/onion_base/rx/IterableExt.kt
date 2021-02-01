package com.ciruy.onion_base.rx

import kotlin.collections.set

fun <K, V> MutableMap<K, V>.createIfNull(key: K, vProvider: () -> V): V {
    this[key] = this[key] ?: vProvider.invoke()
    return this[key] ?: error("createIfNull: vProvider should not provider nullable object")
}

fun <V> ArrayList<V>.createIfNull(index: Int, vProvider: () -> V): V {
    if (this.size>index)
        return this[index]
    val element = vProvider.invoke()
    this.add(element)
    return element
}