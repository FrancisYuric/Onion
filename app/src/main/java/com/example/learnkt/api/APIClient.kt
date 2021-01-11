package com.example.learnkt.api

import com.example.learnkt.CiruyApplication
import com.example.learnkt.bean.ComparableSoftReference
import com.example.learnkt.bean.Constant
import com.example.learnkt.bean.Constant.FIRIM_URL_BASE
import com.example.learnkt.net.CookieJarImpl
import com.example.learnkt.net.HttpLoggingInterceptorImpl
import com.example.learnkt.util.SoftMemorizers
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
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
        fun instance() = instances
    }

    /**
     * 临时性超时时间设置
     */
    var timeoutInSecond: Long = 30

    fun response() = OkHttpClient().newBuilder().build()
            .newCall(Request.Builder().url(Constant.THUNDER_DOWNLOAD_URL_BASE).build())
            .execute()
    fun <F,T>createIfAbsent(f2t:(F?)->T) = SoftMemorizers.applicable(f2t)
    fun <T> instanceRetrofit(): (Long) -> (String) -> (Class<T>) -> T = { timeout ->
        { baseUrl ->
            { apiInterface ->
                SoftMemorizers.applicable<Long, SoftMemorizers<String, SoftMemorizers<Class<T>, T>>> { mTimeOut ->
                    SoftMemorizers.applicable { mBaseUrl ->
                        SoftMemorizers.applicable { mApiInterface ->
                            Retrofit.Builder()
                                    .client(OkHttpClient().newBuilder()
                                            .addInterceptor(HttpLoggingInterceptorImpl)
                                            .followRedirects(true)
                                            .readTimeout(mTimeOut ?: 30, TimeUnit.SECONDS)
                                            .writeTimeout(mTimeOut ?: 30, TimeUnit.SECONDS)
                                            .connectTimeout(mTimeOut ?: 30, TimeUnit.SECONDS)
                                            .cache(Cache(File(CiruyApplication.instance?.cacheDir, "cache"), (1024 * 1024 * 100).toLong()))
                                            .cookieJar(CookieJarImpl).build())
                                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create()))
                                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                    .baseUrl(mBaseUrl)
                                    .build().create(mApiInterface)
                        }
                    }
                }.computeIfAbsentOrigin(ComparableSoftReference(timeout))?.get()?.computeIfAbsentOrigin(ComparableSoftReference(baseUrl))?.get()?.computeIfAbsentOrigin(ComparableSoftReference(apiInterface))?.get()!!
            }
        }
    }

    fun <T> instanceRetrofit(baseUrl: String, apiInterface: Class<T>): T = instanceRetrofit<T>()
            .invoke(timeoutInSecond)
            .invoke(baseUrl)
            .invoke(apiInterface)

    //实例化retrofit，面向接口编程
    fun <T> instanceRetrofit(apiInterface: Class<T>): T = instanceRetrofit(FIRIM_URL_BASE, apiInterface)
}