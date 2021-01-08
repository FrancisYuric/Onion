package com.example.learnkt.bean

import com.example.learnkt.api.APIClient
import com.example.learnkt.api.WanAndroidAPI

object NetConstant {
    fun firm_download() = APIClient.instances.instanceRetrofit(Constant.FIRIM_URL_BASE, WanAndroidAPI::class.java).firApkSize80M()

     fun thunder_download() =
            APIClient.instances.instanceRetrofit(WanAndroidAPI::class.java).firApkSize80M()
}