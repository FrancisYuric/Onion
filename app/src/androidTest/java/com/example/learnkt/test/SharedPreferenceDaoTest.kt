package com.example.learnkt.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.learnkt.CiruyApplication
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedPreferenceDaoTest {
    companion object {
        const val TEST_KEY = "instrumentedTest"
        const val TEST_STRING = "ciruy"
    }

    @Before
    fun setUp() {
        sharedPreferenceDao = SharedPreferenceDao(CiruyApplication.instance!!.baseContext)
    }

    lateinit var sharedPreferenceDao: SharedPreferenceDao
    @Test
    fun sharedPreferenceDaoWriteRead() = run {
        sharedPreferenceDao.put(TEST_KEY, TEST_STRING)
        Assert.assertEquals(TEST_STRING, sharedPreferenceDao.get(TEST_KEY))
    }


}