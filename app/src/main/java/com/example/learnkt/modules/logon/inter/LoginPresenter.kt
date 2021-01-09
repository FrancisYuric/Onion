package com.example.learnkt.modules.logon.inter

import android.content.Context
import com.example.learnkt.inter.IPresenter

//Presenter层
class LoginPresenter : IPresenter<LoginModel, LoginView, LoginPresenter> {

    //登录
    fun loginAction(context: Context,
                    username: String,
                    password: String) {

    }


}