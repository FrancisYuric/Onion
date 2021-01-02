package com.example.learnkt.bean.rx

import android.content.Context
import com.example.learnkt.util.LoadingDialog

abstract class LoadingAPIResultObserver<T>(private val context: Context) : APIResultObserver<T>() {

    override fun start() {
        LoadingDialog.show(context)
    }

    override fun success(target: T?) {
        LoadingDialog.hide()
    }

    override fun fail(errMes: Throwable) {
        LoadingDialog.hide()
    }
}