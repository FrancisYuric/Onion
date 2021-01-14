package com.example.learnkt.util

import android.util.Pair
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ciruy.b.heimerdinger.onion_view.bind2Api
import com.example.learnkt.CiruyApplication
import com.example.learnkt.bean.ResponseError
import com.example.learnkt.listener.DownloadApkListener
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody


fun View.bind2ProgressDownload(flowable: Flowable<ResponseBody>): Flowable<Pair<String, Int>> = this.bind2Api(flowable).progressDownload()

fun Flowable<ResponseBody>.progressDownload(): Flowable<Pair<String, Int>> = this.observeOn(Schedulers.io())
        .onErrorReturn { ResponseError.error(it) }
        .flatMap { t: ResponseBody ->
            if (t is ResponseError) Flowable.just(Pair(t.throwable.toString(), -1))
            else object : Observable<Pair<String, Int>>() {
                override fun subscribeActual(observer: Observer<in Pair<String, Int>>?) {
                    val downloadApkListener = DownloadApkListener(observer)
                    observer?.onSubscribe(downloadApkListener)
                    RequestUtil.writeResponseBodyToDisk(t, CiruyApplication.instance?.getExternalDownloadLocalFilesDir()
                            + System.currentTimeMillis() + ".apk", downloadApkListener)
                }
            }.toFlowable(BackpressureStrategy.DROP)
        }
        .observeOn(AndroidSchedulers.mainThread())
