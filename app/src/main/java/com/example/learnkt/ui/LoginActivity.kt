package com.example.learnkt.ui

import com.example.learnkt.R
import com.example.learnkt.inter.ResultListener
import com.example.learnkt.modules.logon.inter.LoginModel
import com.example.learnkt.modules.logon.inter.LoginView
import com.example.learnkt.ui.baseActivity.BaseDisposableActivity
import com.example.learnkt.util.LogUtil
import com.example.learnkt.util.ToastUtil
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.ac_login.*

class LoginActivity : BaseDisposableActivity(), LoginView {
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
            loginAction(this@LoginActivity,
                    et_username.text.toString(),
                    et_password.text.toString(),
                    object : ResultListener<LoginModel> {
                        override fun success(t: LoginModel) {

                        }

                        override fun failure(errMes: String?) = ToastUtil.short(this@LoginActivity, errMes)
                    })
        })
    }

}