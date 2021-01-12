package com.example.learnkt.api

import org.junit.Test


class APIClientTest {

//    @Test
    fun instanceRetrofit() {
        println(APIClient.instance().instanceRetrofit("http://www.baidu.com", WanAndroidAPI::class.java))
        println(APIClient.instance().instanceRetrofit("http://www.baidu.com", WanAndroidAPI::class.java))
        println(APIClient.instance().instanceRetrofit("http://www.baidu.com", WanAndroidAPI::class.java))
        println(APIClient.instance().instanceRetrofit("http://www.baidu.com", WanAndroidAPI::class.java))
        println(APIClient.instance().instanceRetrofit("http://www.baidu.com", WanAndroidAPI::class.java))
    }
}