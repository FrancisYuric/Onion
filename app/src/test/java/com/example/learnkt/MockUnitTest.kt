package com.example.learnkt

import android.content.Context
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
//太奇葩了，竟然不知道自动处理
class MockUnitTest (){
    companion object {
        const val FAKE_STRING = "AndroidUnitTest"

        @Mock
        lateinit var mMockContext: Context

    }


    @Test
    fun readStringFromContext_LocalizedString() {
        `when`(mMockContext.getString(R.string.app_name)).thenReturn(FAKE_STRING)
        mMockContext.getString(R.string.app_name).eq(FAKE_STRING)
        `when`(mMockContext.packageName).thenReturn("com.jdqm.androidunittest")
        println(mMockContext.packageName)
    }
}