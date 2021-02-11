package com.ciruy.onion_base.memory

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class WeakMemorizersTest{
    @Test
    fun testWeak(){
        var any :Any?= Any()
        val memorizers = WeakMemorizers.applicable<Any?,String> {
            println("function start")
            "123"
        }
        assertEquals("123",memorizers.computeIfAbsent(any))
        any = null
        System.gc()
        assertEquals("123",memorizers.computeIfAbsent(any))
        any = 1L
        assertEquals("123",memorizers.computeIfAbsent(any))
        memorizers.forget(any)
        assertEquals("123",memorizers.computeIfAbsent(any))
        assertEquals("123",memorizers.computeIfAbsent(any))
    }

}