package com.example.learnkt.bean

import org.junit.Assert.assertEquals
import org.junit.Test

class ConstantTest {
    @Test
    fun testUrlPattern() {
        assertEquals(true, legalUrl("http://192.168.90.98"))
        assertEquals(false, legalUrl("192.168.0.98"))
        assertEquals(false, legalUrl("atp:192.168.0.98"))

        //ip地址只写三位时会出问题
        assertEquals(false, legalUrl("http://192.168.0"))
    }
}