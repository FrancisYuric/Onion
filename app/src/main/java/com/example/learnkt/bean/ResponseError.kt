package com.example.learnkt.bean

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.BufferedSource

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

