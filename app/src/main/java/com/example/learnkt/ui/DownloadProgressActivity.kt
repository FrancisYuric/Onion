package com.example.learnkt.ui

import android.text.TextUtils
import com.app.common_upload.annotation.apt.BuilderClass
import com.app.common_upload.inter.ISubBuilder
import com.example.learnkt.R
import com.example.learnkt.bean.NetConstant
import com.example.learnkt.ui.baseActivity.BaseDisposableActivity
import com.example.learnkt.util.ToastUtil
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*

@BuilderClass
class DownloadProgressActivity : BaseDisposableActivity(), ISubBuilder<SubDownloadProgressActivity> {
    override fun subBuilder() = SubDownloadProgressActivity.__create(this)

    override fun layout(): Int = R.layout.activity_main

    override fun initListeners() {
        helloWorld.download(NetConstant.firm_download(), Consumer {
            if (TextUtils.isEmpty(it.first)) helloWorld.text = "下载进度：${it.second}%"
            else ToastUtil.long(this, it.first)
        })
    }
}
