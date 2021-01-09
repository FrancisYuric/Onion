package com.example.learnkt.ui

import android.text.Editable
import com.example.learnkt.R
import com.example.learnkt.ui.baseActivity.BaseDisposableActivity
import com.example.learnkt.util.LogUtil
import com.example.learnkt.util.ToastUtil
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.ac_login.*

class LoginActivity : BaseDisposableActivity() {
    override fun layout(): Int = R.layout.ac_login
    override fun initListeners() {
        super.initListeners()
        et_username.textChanges(Consumer {
            LogUtil.e("change username to $it.toString()")
        })
        et_password.textChanges(Consumer {
            LogUtil.e("change password to $it.toString()")
        })
        btn_login.click(Consumer {
            if(et_username.text.isNotEmpty()&&et_password.text.isNotEmpty())
            doLogIn(et_username.text,et_password.text)
            else ToastUtil.short(this, "请输入用户名和密码！")
        })
    }

    /**
     * 进行登录操作
     */
    private fun doLogIn(text: Editable?, text1: Editable?) {

    }
}