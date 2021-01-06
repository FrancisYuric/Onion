package com.example.learnkt.ui

import android.text.TextUtils
import com.example.learnkt.R
import com.example.learnkt.api.APIClient
import com.example.learnkt.api.WanAndroidAPI
import com.example.learnkt.bean.Constant
import com.example.learnkt.ui.baseActivity.BaseDisposableActivity
import com.example.learnkt.util.ToastUtil
import com.example.learnkt.util.bind2ProgressDownload
import kotlinx.android.synthetic.main.activity_main.*

class DownloadProgressActivity : BaseDisposableActivity() {
    override fun layout(): Int = R.layout.activity_main

    override fun initListeners() {
//        helloWorld.bind(APIClient.instances.instanceRetrofit(Constant.THUNDER_DOWNLOAD_URL_BASE,WanAndroidAPI::class.java).downloadThunderDmgSize22M())
//                .invoke {
//                    Consumer {  }
//                }
//        addDisposable(helloWorld.onion<TextView, Flowable<ResponseBody>, Flowable<Pair<String, Int>>> { pair ->
//            pair.first.bind2ProgressDownload(pair.second)
//        }.invoke {
//            APIClient.instances.instanceRetrofit(Constant.THUNDER_DOWNLOAD_URL_BASE, WanAndroidAPI::class.java).downloadThunderDmgSize22M()
//        }.subscribe {
//            if (TextUtils.isEmpty(it.first)) helloWorld.text = "下载进度：${it.second}%"
//            else ToastUtil.long(this, it.first)
//        })
        addDisposable(helloWorld.bind2ProgressDownload(APIClient.instances.instanceRetrofit(Constant.THUNDER_DOWNLOAD_URL_BASE, WanAndroidAPI::class.java)
                .downloadThunderDmgSize22M())
                .subscribe {
                    if (TextUtils.isEmpty(it.first)) helloWorld.text = "下载进度：${it.second}%"
                    else ToastUtil.long(this, it.first)
                })
    }

}


