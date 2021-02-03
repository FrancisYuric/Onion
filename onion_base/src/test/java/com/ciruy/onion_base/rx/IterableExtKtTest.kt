package com.ciruy.onion_base.rx

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class IterableExtKtTest {
    @Test
    fun mapCreateIfNull() {
        val map = mutableMapOf<String, String>()
        map["1"] = "1"
        map["2"] = "2"
        map.createIfNull("3") { "3" }
        assertEquals(map["3"], "3")
    }

    @Test
    fun listCreateIfNull() {
        val list = arrayListOf<String>()
        list.add("0")
        list.add("1")
        list.createIfNull(2) { "2" }

        assertEquals("2", list[2])
    }
}