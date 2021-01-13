package com.example.learnkt.bean

import ciruy.b.heimerdinger.annotation.BuilderClass
import java.lang.Exception

data class DownloadInfo(var url: String,
                        var total: Long,
                        var progress: Long,
                        var mFileName: String) {

    @Throws(Exception::class)
    fun aaa(){}
    companion object {
        const val TOTAL_ERROR: Long = -1
    }
}