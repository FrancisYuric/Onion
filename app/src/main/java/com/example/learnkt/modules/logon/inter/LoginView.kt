package com.example.learnkt.modules.logon.inter

import android.content.Context
import com.example.learnkt.inter.IView
import com.example.learnkt.inter.ResultListener
import java.lang.ref.WeakReference

interface LoginView : IView<LoginModel, LoginView, LoginPresenter> {
    override fun presenter() = LoginPresenter()

    //登录
    fun loginAction(context: WeakReference<Context>,
                    username: String,
                    password: String,
                    resultListener: ResultListener<LoginModel>) =
            presenter().loginAction(context, username, password, resultListener)

}