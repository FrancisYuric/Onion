package com.example.learnkt.bean

import android.widget.EditText
import com.example.learnkt.api.APIClient
import com.example.learnkt.api.SupconApi
import com.example.learnkt.api.WanAndroidAPI

object NetConstant {
    /**
     * Firm 80M安装包下载
     */
    fun firm_download() = APIClient.instance().instanceRetrofit(Constant.FIRIM_URL_BASE, WanAndroidAPI::class.java).firApkSize80M()

    /**
     * Thunder 20M安装包
     */
    fun thunder_download() = APIClient.instance().instanceRetrofit(Constant.THUNDER_DOWNLOAD_URL_BASE, WanAndroidAPI::class.java).downloadThunderDmgSize22M()

    fun supcon_login(url: String,
                     username: String,
                     password: String) = APIClient.instance()
            .instanceRetrofit(url, SupconApi::class.java)
            .login(username, password, hashMapOf())

}