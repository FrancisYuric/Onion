package com.example.learnkt.modules.logon.inter

import android.util.Log
import com.example.learnkt.modules.logon.bean.LoginResultEntity

interface LoginView {
    //结果显示到Activity/Fragment
    fun loginSuccess(loginResultEntity: LoginResultEntity)

    fun loginFailure(errMes:String?)
}