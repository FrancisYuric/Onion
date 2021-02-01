package com.ciruy.onion_base.rx

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class IterableExtKtTest{
    @Test
    fun createIfNull(){
        val map  = mutableMapOf<String,String>()
        map["1"] = "1"
        map["2"]= "2"
        map.createIfNull("3",{"3"})
        assertEquals(map["3"],"3")
    }
}