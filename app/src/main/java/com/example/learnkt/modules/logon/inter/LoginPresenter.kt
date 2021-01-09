package com.example.learnkt.modules.logon.inter

import android.content.Context
import com.example.learnkt.inter.IPresenter
import com.example.learnkt.inter.ResultListener

//Presenter层
open class LoginPresenter : IPresenter<LoginModel, LoginView, LoginPresenter> {
    override fun model() = LoginModel()

    //登录
    fun loginAction(context: Context,
                    username: String,
                    password: String) :ResultListener<LoginModel> {
        return model()
    }


}