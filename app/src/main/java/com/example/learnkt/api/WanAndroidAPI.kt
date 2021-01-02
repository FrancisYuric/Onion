package com.example.learnkt.api

import com.example.learnkt.bean.LoginResultEntity
import com.example.learnkt.bean.ResultEntityWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Streaming

//客户端API
interface WanAndroidAPI {
    @POST("/user/login")
    @FormUrlEncoded
    fun loginAction(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<ResultEntityWrapper<LoginResultEntity>>

//    @POST()
//    @Streaming
//    fun download():

}