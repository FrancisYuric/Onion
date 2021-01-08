package com.example.learnkt.api

import android.util.Log
import com.example.learnkt.CiruyApplication
import com.example.learnkt.bean.Constant
import com.example.learnkt.bean.Constant.FIRIM_URL_BASE
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

    /**
     * 临时性超时时间设置
     */
    var timeoutInSecond: Int = 30

    fun response() =
            OkHttpClient().newBuilder().build().newCall(
                    Request.Builder().url(Constant.THUNDER_DOWNLOAD_URL_BASE)
                            .build()
            )
                    .execute()

    fun <T> instanceRetrofit(baseUrl: String,
                             apiInterface: Class<T>): T {
        val log = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.e("ciruy", message)
            }
        })
        log.level = HttpLoggingInterceptor.Level.HEADERS
        val gson = GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create()
        val cacheFile = File(CiruyApplication.instance?.cacheDir, "cache")
        val cache = Cache(cacheFile, (1024 * 1024 * 100).toLong()) //100Mb
        return Retrofit.Builder()
                .client(
                        OkHttpClient().newBuilder()
//                                .addInterceptor(object : Interceptor {
//                                    override fun intercept(chain: Interceptor.Chain): Response {
//                                        Log.e("ciruy", chain.call().request().url.encodedPath)
//                                        return chain.proceed(chain.request())
//                                    }
//
//                                })
                                .addInterceptor(log)
                                .followRedirects(true)
                                .readTimeout(1000, TimeUnit.SECONDS)
                                .connectTimeout(1000, TimeUnit.SECONDS)
                                .cache(cache)
                                .writeTimeout(1000, TimeUnit.SECONDS).build()
                )
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build().create(apiInterface)
    }

    //实例化retrofit，面向接口编程
    fun <T> instanceRetrofit(apiInterface: Class<T>): T = instanceRetrofit(FIRIM_URL_BASE, apiInterface)
}