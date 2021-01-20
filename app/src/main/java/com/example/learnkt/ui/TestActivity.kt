package com.example.learnkt.ui

import com.ciruy.b.heimerdinger.onion_view.view.content
import com.example.learnkt.R
import com.example.learnkt.modules.example.TestView
import com.example.learnkt.ui.baseActivity.BaseDisposableActivity
import com.example.learnkt.util.ToastUtil
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : BaseDisposableActivity(), TestView {
    override fun layout(): Int = R.layout.activity_test
    override fun initListeners() {
        super.initListeners()
        helloWorld.lazyBind { presenter().login(helloWorld.content, "frank", "1") }
                .invoke { if(it.errMsg.isEmpty()) ToastUtil.short(this, "登录成功！")
                else ToastUtil.short(this, it.errMsg)}
    }

}