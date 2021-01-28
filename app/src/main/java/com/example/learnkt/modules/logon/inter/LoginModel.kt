package com.example.learnkt.modules.logon.inter

import android.content.Context
import com.example.learnkt.api.APIClient
import com.example.learnkt.api.SupconApi
import com.example.learnkt.bean.supcon_login
import com.example.learnkt.inter.IModel
import com.example.learnkt.inter.ResultListener
import java.lang.ref.Reference

open class LoginModel : IModel<LoginModel> {

    /**
     * 登录操作
     */
    fun login(context: Reference<Context>, username: String, password: String,
              resultListener: ResultListener<LoginModel>) {
        //基于context，username，password进行登录的操作
        supcon_login("10.6.0.92", username, password).subscribe {
            //            resultListener.success(it)
        }
//        callback(resultListener)
    }

    /**
     * 取消请求操作
     */
    fun cancelRequest() {

    }
}