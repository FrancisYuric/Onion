package com.example.learnkt.bean

import com.example.learnkt.isFalse
import com.example.learnkt.isTrue
import org.junit.Test

class UrlPatternTest {
    @Test
    fun testUrlPattern() {
        legalUrl("http://192.168.90.98").isTrue()
        legalUrl("192.168.0.98").isFalse()
        legalUrl("atp:192.168.0.98").isFalse()
        //ip地址只写三位时会出问题,该正则表达式并没有对于ip的位数做出限制
        legalUrl("http://192.168.0").isFalse()
        legalUrl("http:192.168.90.98").isFalse()
    }
}