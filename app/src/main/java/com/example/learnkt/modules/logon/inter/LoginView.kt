package com.example.learnkt.modules.logon.inter

import android.util.Log
import com.example.learnkt.inter.IView
import com.example.learnkt.modules.logon.bean.LoginResultEntity

interface LoginView:IView {
    //结果显示到Activity/Fragment
    fun loginSuccess(loginResultEntity: LoginResultEntity)

    fun loginFailure(errMes:String?)
}