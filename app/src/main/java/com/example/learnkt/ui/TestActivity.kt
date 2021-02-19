package com.example.learnkt.ui

import com.ciruy.b.heimerdinger.onion_view.ext.bind
import com.ciruy.b.heimerdinger.onion_view.ext.lazyBind
import com.ciruy.b.heimerdinger.onion_view.view.content
import com.ciruy.onion_base.util.ToastUtil
import com.example.learnkt.R
import com.example.learnkt.modules.example.TestView
import com.example.learnkt.ui.baseActivity.BaseDisposableActivity
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : BaseDisposableActivity(R.layout.activity_test), TestView {
    private val loginFun = { presenter().login(helloWorld.content, "frank", "1") }
    override fun initListeners() {
        super.initListeners()
        helloWorld.lazyBind(loginFun) {
            if (it.errMsg.isEmpty()) ToastUtil.short(this, "登录成功！")
            else ToastUtil.short(this, it.errMsg)
        }
        helloWorld.bind(loginFun.invoke()) {
            if (it.errMsg.isEmpty()) ToastUtil.short(this, "登录成功！")
            else ToastUtil.short(this, it.errMsg)
        }
        helloWorld.bind(presenter().login(helloWorld.content, "frank", "1")) {
            if (it.errMsg.isEmpty()) ToastUtil.short(this, "登录成功！")
            else ToastUtil.short(this, it.errMsg)
        }
    }
}