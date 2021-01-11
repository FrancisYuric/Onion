package com.example.learnkt.api

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

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