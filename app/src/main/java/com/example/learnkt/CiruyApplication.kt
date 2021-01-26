package com.example.learnkt

import android.app.Application
import android.os.Environment
import androidx.multidex.MultiDex
import com.ciruy.onion_plugin.SkinManager
import java.io.File

class CiruyApplication : Application() {

    companion object {
        var instance: CiruyApplication? = null
    }

    fun getExternalDownloadLocalFilesDir() = this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separator

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        instance = this
        SkinManager.getInstance(instance!!)
    }
}