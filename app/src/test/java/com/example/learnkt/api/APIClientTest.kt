package com.example.learnkt.api

import org.junit.Test

import org.junit.Assert.*

class APIClientTest {

    @Test
    fun instanceRetrofit() {
        println(APIClient.instance().instanceRetrofit("http://www.baidu.com", WanAndroidAPI::class.java))
        println(APIClient.instance().instanceRetrofit("http://www.baidu.com", WanAndroidAPI::class.java))
        println(APIClient.instance().instanceRetrofit("http://www.baidu.com", WanAndroidAPI::class.java))
        println(APIClient.instance().instanceRetrofit("http://www.baidu.com", WanAndroidAPI::class.java))
        println(APIClient.instance().instanceRetrofit("http://www.baidu.com", WanAndroidAPI::class.java))

    }
}