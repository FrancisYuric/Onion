package com.example.learnkt.ui

import android.annotation.SuppressLint
import android.text.TextUtils
import com.app.common_upload.annotation.apt.BuilderClass
import com.app.common_upload.inter.ISubBuilder
import com.example.learnkt.R
import com.example.learnkt.bean.thunder_download
import com.example.learnkt.ui.baseActivity.BaseDisposableActivity
import com.ciruy.onion_base.util.ToastUtil
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_download_progress.*

@BuilderClass
class DownloadProgressActivity : BaseDisposableActivity(), ISubBuilder<SubDownloadProgressActivity> {
    override
    fun subBuilder(): SubDownloadProgressActivity = SubDownloadProgressActivity.__create(this)

    override fun layout(): Int = R.layout.activity_download_progress

    @SuppressLint("CheckResult")
    override fun initListeners() {
        helloWorld.download(thunder_download()) {
//            if (TextUtils.isEmpty(it.first)) helloWorld.text = getString(R.string.download_progress, it.second)
//            else ToastUtil.long(this, it.first)
        }
        helloWorld.flowableClickUnsafe(Consumer {
            //Todo: do on click helloworld
        })
        helloWorld.flowableClickUnsafe{

        }
        helloWorld.flowableClick {
            //Todo: do on click
        }

        RxView.clicks(helloWorld)
                .subscribe {  }
//        CompositeDisposable().dispose()
    }
}
