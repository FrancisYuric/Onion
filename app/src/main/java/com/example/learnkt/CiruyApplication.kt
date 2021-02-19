package com.example.learnkt

import android.app.Application
import android.os.Environment
import androidx.multidex.MultiDex
import com.ciruy.b.heimerdinger.onion_view.callback.OnionActivityLifecycleCallback
import com.ciruy.onion_plugin.SkinManager
import java.io.File

class CiruyApplication : Application() {

    companion object {
        private var instance: CiruyApplication? = null
        fun instance() = instance!!
    }

    fun getExternalDownloadLocalFilesDir() = "${this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)}${File.separator}"

    override fun onCreate() {
        super.onCreate()
        instance = this
        MultiDex.install(this)
        SkinManager.getInstance(this)
        registerActivityLifecycleCallbacks(OnionActivityLifecycleCallback)
    }
}