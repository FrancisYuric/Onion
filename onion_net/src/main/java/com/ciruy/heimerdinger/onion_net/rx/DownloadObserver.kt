package com.ciruy.heimerdinger.onion_net.rx

import com.ciruy.heimerdinger.onion_net.bean.DownloadInfo
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class DownloadObserver (var disposable:Disposable?,
                                 var downloadInfo: com.ciruy.heimerdinger.onion_net.bean.DownloadInfo?):Observer<com.ciruy.heimerdinger.onion_net.bean.DownloadInfo>{
    override fun onSubscribe(d: Disposable) {
        this.disposable = d
    }

    override fun onNext(t: com.ciruy.heimerdinger.onion_net.bean.DownloadInfo) {
        this.downloadInfo = t
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }
}