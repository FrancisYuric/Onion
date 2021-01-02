package com.example.learnkt.bean

data class ResultEntityWrapper<T>(
    val data: T?,
    val errorCode: Int,
    val errorMsg: String?
)
