package com.example.learnkt

import android.app.Application
import android.os.Environment
import java.io.File

class CiruyApplication : Application() {

    companion object {
        var instance: CiruyApplication? = null
    }

    fun getExternalFilesDir() = this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separator

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}