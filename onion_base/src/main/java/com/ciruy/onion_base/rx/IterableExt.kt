package com.ciruy.onion_base.rx

import kotlin.collections.set

fun <K, V, M : MutableMap<K, V>> M.createIfNull(key: K, vProvider: () -> V): V {
    this[key] = this[key] ?: vProvider.invoke()
    return this[key] ?: error("createIfNull: vProvider should not provider nullable object")
}