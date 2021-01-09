package com.example.learnkt.inter

interface ResultListener<T> {
    fun success(t:T)
    fun failure(errMes:String?)
}