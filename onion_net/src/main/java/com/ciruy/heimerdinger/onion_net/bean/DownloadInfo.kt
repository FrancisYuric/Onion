package com.ciruy.heimerdinger.onion_net.bean

data class DownloadInfo(var url: String,
                        var total: Long,
                        var progress: Long,
                        var mFileName: String) {
    companion object {
        const val TOTAL_ERROR: Long = -1
    }
}