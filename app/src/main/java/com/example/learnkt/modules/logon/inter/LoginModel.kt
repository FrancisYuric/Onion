package com.example.learnkt.modules.logon.inter

import android.content.Context
import android.util.LogPrinter
import com.example.learnkt.inter.IModel

interface LoginModel :IModel<LoginModel,LoginView,LoginPresenter>{
    /**
     * 登录操作
     */
    fun login(context:Context,
              username:String,
              password:String)

    /**
     * 取消请求操作
     */
    fun cancelRequest()
}