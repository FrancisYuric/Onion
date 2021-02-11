package com.ciruy.onion_base.memory

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class StrongMemorizersTest{


    @Test
    fun testStrong(){
        var any :Any?= Any()
        val memorizers = StrongMemorizers<Any?,String> {
            println("function start")
            "123"
        }
        //第一次实际调用
        assertEquals("123",memorizers.computeIfAbsent(any))
        any = null
        System.gc()
        //第二次实际调用
        assertEquals("123",memorizers.computeIfAbsent("123"))
        //复用结果
        assertEquals("123",memorizers.computeIfAbsent("123"))
        memorizers.forget(any)
        //第三次实际调用
        assertEquals("123",memorizers.computeIfAbsent("123"))
        //复用结果
        assertEquals("123",memorizers.computeIfAbsent("123"))
    }
}