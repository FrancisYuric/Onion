package com.example.learnkt.ui

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.example.learnkt.R
import com.example.learnkt.api.APIClient
import com.example.learnkt.api.WanAndroidAPI
import com.example.learnkt.util.LogUtil
import com.example.learnkt.util.RequestUtil
import io.reactivex.Flowable
import io.reactivex.functions.Consumer
import okhttp3.ResponseBody
import java.io.File
import java.sql.Time
import java.util.concurrent.TimeUnit

class DownloadProgressActivity : AppCompatActivity() {


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        RxPermissions(this).requestEachCombined(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
//                .subscribe{
//        APIClient.instances.instanceRetrofit(WanAndroidAPI::class.java)
////                .download()
//            .baidu()
//            .subscribeOn(Schedulers.io())
//            .onErrorReturn {
//                LogUtil.e(it.toString())
//                ResponseError.instance()
//            }
//            .filter { t -> t !== ResponseError.instance() }
//            .subscribe {
//                LogUtil.e(DateFormat.getInstance().format(System.currentTimeMillis()))
//                val LOCAL_PATH =
//                    (application.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString()
//                            + File.separator)
//                RequestUtil.writeResponseBodyToDisk(
//                    it
//                    LOCAL_PATH + System.currentTimeMillis() + ".apk",
//                    null
//                )
//            }
        LogUtil.e("link start")
        val LOCAL_PATH =
            (application.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString()
                    + File.separator)
        Flowable.timer(2, TimeUnit.SECONDS)
            .subscribe {

                RequestUtil.writeResponseBodyToDisk(
                    APIClient.instances
                        .response().body,
//                .instanceRetrofit(WanAndroidAPI::class.java)
//            .baidu()
//            .execute().body(),
                    LOCAL_PATH + System.currentTimeMillis() + "apk",
                    null
                )
            }


//            .enqueue(object : retrofit2.Callback<ResponseBody> {
//                override fun onResponse(
//                    call: retrofit2.Call<ResponseBody>?,
//                    response: retrofit2.Response<ResponseBody>?
//                ) {
//
//                    val LOCAL_PATH =
//                        (application.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString()
//                                + File.separator)
//                    RequestUtil.writeResponseBodyToDisk(
//                        response?.body(),
//                        LOCAL_PATH + System.currentTimeMillis() + ".apk",
//                        null
//                    )
//                }
//
//                override fun onFailure(call: retrofit2.Call<ResponseBody>?, t: Throwable?) {
//
//                }
//
//            })
    }

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
//                .subscribe { Log.e("ciruy","123") }

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