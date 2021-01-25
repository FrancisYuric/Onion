package com.example.learnkt.util

import org.junit.Test

class SoftMemorizersTest{
    @Test
    fun testApplicable(){
        val softMemorizers = SoftMemorizers.applicable<String,String> {
            println("convert from String to String begin ${System.currentTimeMillis()}")
            Thread.sleep(1000)
            println("convert from String to String end ${System.currentTimeMillis()}")
            it!!
        }
        for (i in 0L..9L) {
            softMemorizers.computeIfAbsent(""+i)
        }
    }
}