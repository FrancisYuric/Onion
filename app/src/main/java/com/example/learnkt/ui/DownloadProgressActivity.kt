package com.example.learnkt.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.util.Pair
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learnkt.R
import com.example.learnkt.api.APIClient
import com.example.learnkt.api.WanAndroidAPI
import com.example.learnkt.bean.Constant
import com.example.learnkt.bean.ResponseError
import com.example.learnkt.listener.DownloadApkListener
import com.example.learnkt.util.RequestUtil
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import java.io.File

class DownloadProgressActivity : AppCompatActivity() {


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        APIClient.instances.instanceRetrofit(Constant.THUNDER_DOWNLOAD_URL_BASE, WanAndroidAPI::class.java)
                .downloadThunderDmgSize22M()
                .subscribeOn(Schedulers.io())
                .onErrorReturn {
                    ResponseError.instance()
                }
                .filter { t: ResponseBody ->
                    t !== ResponseError.instance()
                }
                .flatMap { t: ResponseBody ->
                    object : Observable<Pair<String, Int>>() {
                        override fun subscribeActual(observer: Observer<in Pair<String, Int>>?) {
                            val downloadApkListener = DownloadApkListener(observer)
                            observer?.onSubscribe(downloadApkListener)
                            val LOCAL_PATH = (application.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString()
                                    + File.separator)
                            RequestUtil.writeResponseBodyToDisk(t, LOCAL_PATH + System.currentTimeMillis() + ".apk", downloadApkListener)
                        }
                    }.toFlowable(BackpressureStrategy.DROP)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    if (TextUtils.isEmpty(it.first))
                        helloWorld.text = "" + it.second
                    else
                        Toast.makeText(this, "123", Toast.LENGTH_LONG).show()
                }
                .subscribe()
    }
}


