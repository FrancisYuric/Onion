package com.example.learnkt

import android.text.TextUtils
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito.`when`
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(value = [TextUtils::class])
class PowerMock_StaticTest {
    @Test
    @Throws(Exception::class)
    fun testSomething(){
        mockStatic(TextUtils::class.java)
        `when`(TextUtils.isEmpty("")).thenReturn(true)
    }
}