package com.example.learnkt.listener

import androidx.core.util.Pair
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class DownloadApkListener(private val observer: Observer<in Pair<String, Int>>?) : Disposable {
    fun onStart() {

    }

    fun onProgress(p: Int) {
        observer?.onNext(Pair.create(null, p))
    }

    fun onFinish() {
        observer?.onComplete()
    }

    fun onError(throwable: String) {
        observer?.onError(Throwable(throwable))
    }

    override fun dispose() {
    }

    override fun isDisposed(): Boolean {
        return true
    }

}