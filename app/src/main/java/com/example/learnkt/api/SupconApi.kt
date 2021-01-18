package com.example.learnkt.api

import com.example.learnkt.bean.Constant
import com.example.learnkt.bean.supcon.LoginEntity
import com.example.learnkt.bean.supcon.SystemCodeListEntity
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface SupconApi {
    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     * @param map      默认参数
     * @return
     */
    @GET("/cas/mobile/logon")
    fun login(@Query(Constant.ApiQuery.USER_NAME) username: String,
              @Query(Constant.ApiQuery.PASSWORD) password: String, @QueryMap map: Map<String, Any>): Flowable<LoginEntity>

    /**
     * 获取系统编码
     */
    @GET("/foundation/systemCode/codeValueManager/valueList.action?systemCodePage.pageSize=100&systemCodePage.maxPageSize=500")
    fun querySystemCode(@Query(Constant.ApiQuery.SYSTEM_ENTITY_CODES) systemEntityCode: String): Flowable<SystemCodeListEntity>

}