package com.ciruy.onion_base.util

import java.io.Closeable

object IOUtil {
    fun closeAll(vararg closeable: Closeable?) {
        closeable.forEach { it?.close() }
    }
}