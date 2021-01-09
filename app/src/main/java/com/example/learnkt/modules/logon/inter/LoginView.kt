package com.example.learnkt.modules.logon.inter

import com.example.learnkt.inter.IView

class LoginView : IView<LoginModel, LoginView, LoginPresenter> {
    override fun presenter() = LoginPresenter()
}