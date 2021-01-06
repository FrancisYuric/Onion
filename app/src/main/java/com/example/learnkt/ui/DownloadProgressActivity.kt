package com.example.learnkt.ui

import android.os.Bundle
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
        addDisposable(helloWorld.bind2ProgressDownload(APIClient.instances.instanceRetrofit(Constant.FIRIM_URL_BASE, WanAndroidAPI::class.java).firApkSize80M())
                .subscribe {
                    if (TextUtils.isEmpty(it.first))
                        helloWorld.text = "${it.second}"
                    else
                        ToastUtil.long(this, it.first)
                })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}


