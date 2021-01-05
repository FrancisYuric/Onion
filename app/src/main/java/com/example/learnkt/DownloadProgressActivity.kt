package com.example.learnkt

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import android.util.Pair
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learnkt.api.APIClient
import com.example.learnkt.api.WanAndroidAPI
import com.example.learnkt.bean.ResponseError
import com.example.learnkt.listener.DownloadApkListener
import com.example.learnkt.rx.Permission
import com.example.learnkt.rx.RxPermissions
import com.example.learnkt.util.RequestUtil
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.File
import java.io.IOException

class DownloadProgressActivity : AppCompatActivity() {


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        RxPermissions(this).requestEachCombined(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
//                .subscribe{
        APIClient.instances.instanceRetrofit(WanAndroidAPI::class.java)
//                .download()
                .baidu()
                .subscribeOn(Schedulers.io())
                .onErrorReturn { ResponseError.instance() }
                .filter {t-> t!==ResponseError.instance() }
                .subscribe { Log.e("ciruy","123") }
//                .subscribeOn(Schedulers.io())
//                .onErrorReturn {
//                    ResponseError.instance()
//                }
//                .filter { t: ResponseBody ->
//                    t !== ResponseError.instance()
//                }
//                .flatMap { t: ResponseBody -> object : Observable<Pair<String, Int>>() {
//                    override fun subscribeActual(observer: Observer<in Pair<String, Int>>?) {
//                        val downloadApkListener = DownloadApkListener(observer)
//                        observer?.onSubscribe(downloadApkListener)
//                        val LOCAL_PATH = (application.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString()
//                                + File.separator )
//                        RequestUtil.writeResponseBodyToDisk(t,LOCAL_PATH+System.currentTimeMillis()+".apk",downloadApkListener )
//                    }
//                }.toFlowable(BackpressureStrategy.DROP)
//                }
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext {
//                    if(TextUtils.isEmpty(it.first))
//                        helloWorld.text = ""+it.second
//                    helloWorld.text ="123"
//                    else
//                        Toast.makeText(this, "123",Toast.LENGTH_LONG).show()
//                }
//                .subscribe()
//    }

    }

}