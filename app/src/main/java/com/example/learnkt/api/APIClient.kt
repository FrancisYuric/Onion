package com.example.learnkt.api

import android.util.Log
import com.example.learnkt.CiruyApplication
import com.example.learnkt.bean.Constant
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


class APIClient {
    private object Holder {
        val INSTANCE = APIClient()
    }

    //派生
    companion object {
        val instances = Holder.INSTANCE
    }

    fun response() =
            OkHttpClient().newBuilder().build().newCall(
                    Request.Builder().url(Constant.DOWNLOAD_URL_BASE)
                            .build()
            )
                    .execute()

    val timeUnit = 30L
    //实例化retrofit，面向接口编程
    fun <T> instanceRetrofit(apiInterface: Class<T>): T {
//        val loginInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
//            override fun log(message: String) {
//                LogUtil.e(message)
//            }
//        })
//        loginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val cacheFile = File(CiruyApplication.instance?.cacheDir, "cache")
//        val cache = Cache(cacheFile, 1024 * 1024 * 100)
//
//        val builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
//                .followRedirects(true)
//                .readTimeout(timeUnit, TimeUnit.SECONDS)
//                .connectTimeout(timeUnit, TimeUnit.SECONDS)
//                .writeTimeout(timeUnit, TimeUnit.SECONDS)
//                .cache(cache)
//        val okHttpClient = builder.build()
//
//        val gson = GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create()
//        val retrofit = Retrofit.Builder()
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
////                .baseUrl(getHost()/*isUrlEnabled?"http://"+MBapApp.getUrl()+"/":"http://"+ MBapApp.getIp()+":"+ MBapApp.getPort()+"/"*/)
////                .baseUrl(Constant.DOWNLOAD_URL_BASE)
//                .baseUrl("http://download.fir.im")
//                .build()
//        return retrofit.create(apiInterface)

        val log = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.e("ciruy", message)
            }

        })
        log.level = HttpLoggingInterceptor.Level.HEADERS
        val gson =
                GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls()
                        .create()
        val cacheFile = File(CiruyApplication.instance?.cacheDir, "cache")
        val cache = Cache(cacheFile, (1024 * 1024 * 100).toLong()) //100Mb
        return Retrofit.Builder()
                .client(
                        OkHttpClient().newBuilder()
                                .addInterceptor(object : Interceptor {
                                    override fun intercept(chain: Interceptor.Chain): Response {
                                        Log.e("ciruy", chain.call().request().url.encodedPath)
                                        return chain.proceed(chain.request())
                                    }

                                })
                                .addInterceptor(log)
                                .followRedirects(true)
                                .readTimeout(1000, TimeUnit.SECONDS)
                                .connectTimeout(1000, TimeUnit.SECONDS)
                                .cache(cache)
                                .writeTimeout(1000, TimeUnit.SECONDS).build()
                )
//                .baseUrl(Constant.DOWNLOAD_URL_BASE)
//                .baseUrl("https://down.sandai.net/mac/thunder_3.4.1.4368.dmg")
//                .baseUrl("https://www.baidu.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .baseUrl("http://download.fir.im")
                .build().create(apiInterface)
    }
}