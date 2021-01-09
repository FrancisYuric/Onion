package com.example.learnkt.modules.logon.inter

import android.content.Context
import com.example.learnkt.inter.IPresenter

//Presenter层
class LoginPresenter : IPresenter<LoginModel, LoginView> {
    override fun success(t: LoginModel) {

    }

    override fun failure(errMes: String?) {
    }   //接收Model的结果

    interface OnLoginListener {
        fun loginSuccess(loginModel: LoginModel)

        fun loginFailure(errorMes: String?)
    }

    //登录
    fun loginAction(context: Context,
                    username: String,
                    password: String) {

    }


}