package com.example.learnkt.modules.logon.inter

import android.content.Context
import com.example.learnkt.inter.IPresenter
import com.example.learnkt.inter.ResultListener
import java.lang.ref.WeakReference

//Presenter层
class LoginPresenter : IPresenter<LoginModel, LoginPresenter>() {
    //Todo:懒加载
    override fun model() = LoginModel()

    //登录
    fun loginAction(context: WeakReference<Context>,
                    username: String,
                    password: String,
                    resultListener: ResultListener<LoginModel>) {
        when {
            username.isEmpty() -> resultListener.failure("请输入你的用户名！")
            password.isEmpty() -> resultListener.failure("请输入你的密码！")
            else -> return model.login(context, username, password, resultListener)
        }
    }
}