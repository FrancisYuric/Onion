package com.example.learnkt.api

import android.util.Log
import com.example.learnkt.CiruyApplication
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
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

    //实例化retrofit，面向接口编程
    fun <T> instanceRetrofit(apiInterface: Class<T>): T {
        val log = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.e("ciruy", message)
            }

        })
        log.level = HttpLoggingInterceptor.Level.BODY
        val gson = GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create()
        val cacheFile = File(CiruyApplication.instance?.getCacheDir(), "cache")
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
                                .readTimeout(1000, TimeUnit.SECONDS)
                                .connectTimeout(1000, TimeUnit.SECONDS)
                                .callTimeout(1000, TimeUnit.SECONDS)
                                .cache(cache)
                                .writeTimeout(1000, TimeUnit.SECONDS).build()
                )
                .baseUrl("http://download.fir.im")
//                .baseUrl("https://www.baidu.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .build().create(apiInterface)
    }
}