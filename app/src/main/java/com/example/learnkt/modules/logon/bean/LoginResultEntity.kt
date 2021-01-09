package com.example.learnkt.modules.logon.bean

//登录接口回调数据模型
data class LoginResultEntity(
    val admin: Boolean,
    val chapterTops: ArrayList<*>,
    val collectIds: ArrayList<*>,
    val email: String?,
    val icon: String?,
    val id: Long,
    val nickname: String?,
    val password: String?,
    val publicName: String?,
    val token: String?,
    val type: Int,
    val username: String?
)
