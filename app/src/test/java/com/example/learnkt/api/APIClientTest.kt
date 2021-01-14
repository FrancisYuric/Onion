package com.example.learnkt.api

import org.junit.Test


class APIClientTest {

    @Test
    fun instanceRetrofit() {
        println(APIClient.instance().instanceRetrofit(20, "http://www.baidu.com", WanAndroidAPI::class.java))
        println(APIClient.instance().instanceRetrofit(30, "http://www.baidu.com", WanAndroidAPI::class.java))
        println(APIClient.instance().instanceRetrofit(30, "http://www.xunlei.com", WanAndroidAPI::class.java))
        println(APIClient.instance().instanceRetrofit(20, "http://www.baidu.com", WanAndroidAPI::class.java))
        println(APIClient.instance().instanceRetrofit(30, "http://www.baidu.com", WanAndroidAPI::class.java))
    }
}