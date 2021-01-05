package com.example.learnkt

import android.app.Application

class CiruyApplication :Application(){

    companion object{
        var instance:CiruyApplication? = null

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}