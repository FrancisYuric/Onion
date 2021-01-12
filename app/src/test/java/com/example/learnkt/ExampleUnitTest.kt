package com.example.learnkt

import android.content.Context
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {
    companion object {
        val FAKE_STRING = "AndroidUnitTest"
    }

    @Mock
    lateinit var mMockContext: Context

    @Test
    fun readStringFromContext_LocalizedString() {
        `when`(mMockContext.getString(R.string.app_name)).thenReturn(FAKE_STRING)
        assertThat(mMockContext.getString(R.string.app_name), `is`(FAKE_STRING))

        `when`(mMockContext.packageName).thenReturn("com.ciruy.b.heimerdinger")
        println(mMockContext.packageName)
    }
}
