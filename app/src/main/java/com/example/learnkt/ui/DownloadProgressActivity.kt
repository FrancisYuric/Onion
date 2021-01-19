package com.example.learnkt.ui

import android.text.TextUtils
import com.app.common_upload.annotation.apt.BuilderClass
import com.app.common_upload.inter.ISubBuilder
import com.example.learnkt.R
import com.example.learnkt.bean.thunder_download
import com.example.learnkt.ui.baseActivity.BaseDisposableActivity
import com.example.learnkt.util.ToastUtil
import kotlinx.android.synthetic.main.activity_download_progress.*

@BuilderClass
class DownloadProgressActivity : BaseDisposableActivity(), ISubBuilder<SubDownloadProgressActivity> {
    override
    fun subBuilder() = SubDownloadProgressActivity.__create(this)

    override fun layout(): Int = R.layout.activity_download_progress

    override fun initListeners() {
        helloWorld.download(thunder_download()) {
            if (TextUtils.isEmpty(it.first)) helloWorld.text = getString(R.string.download_progress, it.second)
            else ToastUtil.long(this, it.first)
        }
    }
}
