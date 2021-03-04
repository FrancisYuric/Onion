package com.example.learnkt.ui

import android.annotation.SuppressLint
import android.view.View
import com.app.common_upload.annotation.apt.BuilderClass
import com.app.common_upload.inter.ISubBuilder
import com.ciruy.b.heimerdinger.onion_view.ext.download
import com.ciruy.b.heimerdinger.onion_view.ext.flowableClick
import com.ciruy.b.heimerdinger.onion_view.view.flowableClick
import com.example.learnkt.R
import com.example.learnkt.bean.thunder_download
import com.example.learnkt.ui.baseActivity.BaseDisposableActivity
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_download_progress.*

@BuilderClass
class DownloadProgressActivity : BaseDisposableActivity(R.layout.activity_download_progress), ISubBuilder<SubDownloadProgressActivity> {
    override
    fun subBuilder(): SubDownloadProgressActivity = SubDownloadProgressActivity.__create(this)

    @SuppressLint("CheckResult")
    override fun initListeners() {
        helloWorld.download(thunder_download()) {
            //            if (TextUtils.isEmpty(it.first)) helloWorld.text = getString(R.string.download_progress, it.second)
//            else ToastUtil.long(this, it.first)
        }
        flowableClick<View>(helloWorld).subscribe {
            //Todo: do on click helloworld
        }
        helloWorld.flowableClick {
            //Todo: do on click
        }

        RxView.clicks(helloWorld)
                .subscribe { }
//        CompositeDisposable().dispose()
    }
}
