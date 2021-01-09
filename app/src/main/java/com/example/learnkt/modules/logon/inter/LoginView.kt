package com.example.learnkt.modules.logon.inter

import android.util.Log
import com.example.learnkt.inter.IView
import com.example.learnkt.modules.logon.bean.LoginResultEntity

class LoginView:IView<LoginModel,LoginView,LoginPresenter> {
    override fun presenter() = LoginPresenter()
}