package com.example.learnkt.ui

import com.example.learnkt.R
import com.example.learnkt.ui.baseActivity.BaseDisposableActivity

class TestStackOverflowActivity : BaseDisposableActivity(R.layout.activity_test) {
    override fun initListeners() {
        super.initListeners()
        testStackOverFlow(10000)
    }

    private fun testStackOverFlow(a: Int): Int = if (a < 0) 0 else (testStackOverFlow(a - 1) + testStackOverFlow(a - 2))
}