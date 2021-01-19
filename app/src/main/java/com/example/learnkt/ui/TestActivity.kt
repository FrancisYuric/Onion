package com.example.learnkt.ui

import com.ciruy.b.heimerdinger.onion_view.view.content
import com.example.learnkt.R
import com.example.learnkt.modules.example.TestView
import com.example.learnkt.ui.baseActivity.BaseDisposableActivity
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : BaseDisposableActivity(), TestView {
    override fun layout(): Int = R.layout.activity_test
    override fun initListeners() {
        super.initListeners()
        helloWorld.lazyBind {
            presenter().login(helloWorld.content, helloWorld.content, helloWorld.content)
        }.invoke {

        }
    }
}