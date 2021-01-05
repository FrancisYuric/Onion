package com.example.learnkt.util

import java.io.Closeable

object IOUtil {
    fun closeAll(vararg closeable: Closeable?) {
        closeable.forEach { it?.close() }
    }
}