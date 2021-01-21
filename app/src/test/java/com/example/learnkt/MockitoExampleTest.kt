package com.example.learnkt

import com.example.learnkt.test.ExampleClass
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class MockitoExampleTest {
    @Mock
    lateinit var exampleClass: ExampleClass

    @Throws(Exception::class)
    @Test
    fun mockitoTestExample() {
        val test = mock(ExampleClass::class.java)
        `when`(test.uniqueId).thenReturn(18)
        `when`(test.compareTo(ArgumentMatchers.anyInt())).thenReturn(18)
        doThrow(NullPointerException()).`when`(test).close()
        doNothing().`when`(test).execute()

        assertThat(test.uniqueId, `is`(18))
        // 验证是否调用了1次test.getUniqueId()
        verify(test, times(1)).uniqueId
        reset(test)
        // 验证是否没有调用过test.getUniqueId()
        verify(test, never()).uniqueId
        // 验证是否至少调用过2次test.getUniqueId()
        verify(test, atLeast(2)).uniqueId
        // 验证是否最多调用过3次test.getUniqueId()
        verify(test, atMost(3)).uniqueId
        // 验证是否这样调用过:test.query("test string")
        verify(test).query("test string")
        // 通过Mockito.spy() 封装List对象并返回将其mock的spy对象
        val list = LinkedList<String>()
        val spy = spy(list)
        //指定spy.get(0)返回"Jdqm"
        doReturn("Jdqm").`when`(spy).get(0)
        assertEquals("Jdqm", spy.get(0))
    }
}