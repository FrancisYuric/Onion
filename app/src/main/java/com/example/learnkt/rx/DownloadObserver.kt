package com.example.learnkt.rx

import com.example.learnkt.bean.DownloadInfo
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class DownloadObserver (var disposable:Disposable?,
                                 var downloadInfo: DownloadInfo?):Observer<DownloadInfo>{
    override fun onSubscribe(d: Disposable) {
        this.disposable = d
    }

    override fun onNext(t: DownloadInfo) {
        this.downloadInfo = t
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }
}