package com.example.learnkt.modules.logon.inter

import android.content.Context
import com.example.learnkt.inter.IModel
import com.example.learnkt.inter.ResultListener

open class LoginModel : IModel<LoginModel> {

    /**
     * 登录操作
     */
    fun login(context: Context,
              username: String,
              password: String,
              resultListener: ResultListener<LoginModel>): ResultListener<LoginModel> {
        //基于context，username，password进行登录的操作
        return callback(resultListener)
    }

    /**
     * 取消请求操作
     */
    fun cancelRequest() {

    }
}