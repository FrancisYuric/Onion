package com.example.learnkt.api

import com.example.learnkt.bean.LoginResultEntity
import com.example.learnkt.bean.ResultEntityWrapper
import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

//客户端API
interface WanAndroidAPI {
    @POST("/user/login")
    @FormUrlEncoded
    fun loginAction(
            @Field("username") username: String,
            @Field("password") password: String
    ): Observable<ResultEntityWrapper<LoginResultEntity>>

    /**
     * @see com.example.learnkt.bean.Constant.THUNDER_DOWNLOAD_URL_BASE
     */
    @GET("mac/thunder_3.4.1.4368.dmg")
    @Streaming
    fun downloadThunderDmg(): Flowable<ResponseBody>

    /**
     * @see com.example.learnkt.bean.Constant.FIRIM_URL_BASE
     */
    @GET("apps/5eccda86b2eb4670747962c3/install?download_token=13bcbbe50f70729766c88f4573cf12ef")
    @Streaming
    fun baidu(): Flowable<ResponseBody>
}