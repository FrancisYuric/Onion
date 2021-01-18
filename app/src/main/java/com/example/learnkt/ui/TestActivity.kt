package com.example.learnkt.ui

import com.example.learnkt.R
import com.example.learnkt.modules.example.TestView
import com.example.learnkt.ui.baseActivity.BaseDisposableActivity
import com.example.learnkt.util.LogUtil
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : BaseDisposableActivity(), TestView {
    override fun layout(): Int = R.layout.activity_test
    override fun initListeners() {
        super.initListeners()
        helloWorld.lazyBind {
            presenter().method2(helloWorld.text.toString(), helloWorld.text.toString(), helloWorld.text.toString())
        }.invoke {
            
        }
    }
}