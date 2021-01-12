package com.example.learnkt.modules.hengyi.api

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface NetWorkAPI{
    @GET("/cas/mobile/logon")
    fun login(@Query("username") username:String,
              @Query("password") password:String)
}