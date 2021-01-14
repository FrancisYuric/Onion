package com.example.learnkt.bean

import ciruy.b.heimerdinger.annotation.BuilderClass
import java.lang.Exception

@BuilderClass
data class DownloadInfo(var url: String,
                        var total: Long,
                        var progress: Long,
                        var mFileName: String) {
    companion object {
        const val TOTAL_ERROR: Long = -1
    }
}