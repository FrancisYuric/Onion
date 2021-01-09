package com.example.learnkt.modules.logon.inter

import android.content.Context

//Presenter层
interface LoginPresenter {
    //登录
    fun loginAction(context:Context,username:String, password:String)

}