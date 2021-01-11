package com.example.learnkt.ui

import android.text.TextUtils
import com.example.learnkt.R
import com.example.learnkt.api.APIClient
import com.example.learnkt.api.WanAndroidAPI
import com.example.learnkt.bean.NetConstant
import com.example.learnkt.ui.baseActivity.BaseDisposableActivity
import com.example.learnkt.util.ToastUtil
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*

class DownloadProgressActivity : BaseDisposableActivity() {
    override fun layout(): Int = R.layout.activity_main

    override fun initListeners() {
//        helloWorld.download(NetConstant.firm_download(), Consumer {
//            if (TextUtils.isEmpty(it.first)) helloWorld.text = "下载进度：${it.second}%"
//            else ToastUtil.long(this, it.first)
//        })
        println(
            APIClient.instance().instanceRetrofit(
                15,
                "http://www.baidu.com",
                WanAndroidAPI::class.java
            )
        )
        println(
            APIClient.instance().instanceRetrofit(
                20,
                "http://www.baidu.com",
                WanAndroidAPI::class.java
            )
        )

        println(
            APIClient.instance().instanceRetrofit(
                20,
                "http://www.ciruy.com",
                WanAndroidAPI::class.java
            )
        )
        println(
            APIClient.instance().instanceRetrofit(
                15,
                "http://www.baidu.com",
                WanAndroidAPI::class.java
            )
        )
        println(
            APIClient.instance().instanceRetrofit(
                20,
                "http://www.baidu.com",
                WanAndroidAPI::class.java
            )
        )
    }
}
