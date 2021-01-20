package com.example.learnkt.net

import com.example.learnkt.util.LogUtil
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

object HttpLoggingInterceptorImpl : Interceptor {
    const val TAG = "HttpLoggingInterceptor"
    private val httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            LogUtil.d(TAG, message)
        }
    }).setLevel(HttpLoggingInterceptor.Level.BODY)

    override fun intercept(chain: Interceptor.Chain): Response = httpLoggingInterceptor.intercept(chain)
}