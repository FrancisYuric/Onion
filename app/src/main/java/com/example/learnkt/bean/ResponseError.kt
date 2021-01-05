package com.example.learnkt.bean

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.BufferedSource
import java.lang.IllegalStateException

 class ResponseError :ResponseBody() {
     override fun contentType(): MediaType? {
         throw IllegalStateException()
     }

     override fun source(): BufferedSource {
         throw IllegalStateException()
     }

     override fun contentLength(): Long {
         throw IllegalStateException()
     }

    private object Holder{
        val INSTANCE = ResponseError()
    }
    companion object {
        fun instance():ResponseError{
           return Holder.INSTANCE
        }
    }
}

