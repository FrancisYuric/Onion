package com.ciruy.heimerdinger.onion_net.bean

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.BufferedSource

/**
 * 通用调用失败回调
 */
class ResponseError(var throwable: Throwable?) : ResponseBody() {
    constructor() : this(null)

    override fun contentType(): MediaType? {
        throw IllegalStateException()
    }

    override fun source(): BufferedSource {
        throw IllegalStateException()
    }

    override fun contentLength(): Long {
        throw IllegalStateException()
    }

    private object Holder {
        val INSTANCE = ResponseError()
    }

    companion object {
        fun instance() = Holder.INSTANCE

        fun error(throwable: Throwable) = ResponseError(throwable)
    }
}

