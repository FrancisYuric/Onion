package com.example.learnkt.ui

import com.ciruy.b.heimerdinger.onion.weakR
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
        et_username.flowableTextChanges {
            LogUtil.e("change username to $it.toString()")
        }
        et_password.flowableTextChanges{
            LogUtil.e("change password to $it.toString()")
        }
        //Todo：这个方式可能会引起的内存泄漏问题
        btn_login.flowableClick {
            loginAction(weakR(), et_username.text.toString(), et_password.text.toString(),
                    object : ResultListener<LoginModel> {
                        override fun success(t: LoginModel) {

                        }

                        override fun failure(errMes: String?) = ToastUtil.short(this@LoginActivity, errMes)
                    })
        }
    }

}