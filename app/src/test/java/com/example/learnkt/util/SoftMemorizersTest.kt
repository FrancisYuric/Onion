package com.example.learnkt.util

import com.example.learnkt.bean.ComparableSoftReference
import org.junit.Test

class SoftMemorizersTest{
    @Test
    fun testApplicable(){
        val softMemorizers = SoftMemorizers.applicable<Long,String> {
            println("convert from Long to String")
            ""+it
        }
        for (i in 0L..9L) {
            softMemorizers.forgetOrigin(ComparableSoftReference(1))
            softMemorizers.computeIfAbsentOrigin(ComparableSoftReference(1))
        }
    }
}