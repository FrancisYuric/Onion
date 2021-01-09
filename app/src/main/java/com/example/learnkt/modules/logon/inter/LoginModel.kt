package com.example.learnkt.modules.logon.inter

import android.content.Context

interface LoginModel {
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