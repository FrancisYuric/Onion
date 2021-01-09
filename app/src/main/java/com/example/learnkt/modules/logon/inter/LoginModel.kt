package com.example.learnkt.modules.logon.inter

import android.content.Context
import android.util.LogPrinter
import com.example.learnkt.inter.IModel

open class LoginModel :IModel<LoginModel>{
    override fun success(t: LoginModel) {

    }

    override fun failure(errMes: String?) {
    }

    /**
     * 登录操作
     */
    fun login(context:Context,
              username:String,
              password:String){

    }

    /**
     * 取消请求操作
     */
    fun cancelRequest(){

    }
}