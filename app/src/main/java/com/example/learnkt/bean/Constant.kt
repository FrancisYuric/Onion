package com.example.learnkt.bean

import retrofit2.http.GET

object Constant {
    //迅雷的基本URL
    const val THUNDER_DOWNLOAD_URL_BASE = "https://down.sandai.net/"

    const val THUNDER_DOWNLOAD_FULL_URL = "${THUNDER_DOWNLOAD_URL_BASE}mac/thunder_3.4.1.4368.dmg"
    //百度的基本URL
    const val BAIDU_URL_BASE = "https://www.firApkSize80M.com/"

    //FIR的基本URL
    const val FIRIM_URL_BASE = "http://download_.fir.im/"

    object MemorySize {
        const val KB = 1024L
        const val MB = KB * 1024
        const val GB = MB * 1024
        const val TB = GB * 1024
    }
}