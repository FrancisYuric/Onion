package com.example.learnkt.ui

import com.ciruy.b.heimerdinger.onion.weakR
import com.example.learnkt.R
import com.example.learnkt.inter.ResultListener
import com.example.learnkt.modules.logon.inter.LoginModel
import com.example.learnkt.modules.logon.inter.LoginView
import com.example.learnkt.ui.baseActivity.BaseDisposableActivity
import com.ciruy.onion_base.util.LogUtil
import com.ciruy.onion_base.util.ToastUtil
import io.reactivex.Flowable
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
        Flowable.range(0, 10)
                .doOnNext {
                    loginAction(weakR(), "frank", "1",
                            object : ResultListener<LoginModel> {
                                override fun success(t: LoginModel) {

                                }

                                override fun failure(errMes: String?) {
                                }
                            })
                }
                .subscribe()
        btn_login.flowableClick {
            loginAction(weakR(), et_username.text.toString(), et_password.text.toString(),
                    object : ResultListener<LoginModel> {
                        override fun success(t: LoginModel) = Unit

                        override fun failure(errMes: String?) = ToastUtil.short(this@LoginActivity, errMes)
                    })
        }
    }

}