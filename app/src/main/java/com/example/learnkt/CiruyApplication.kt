package com.example.learnkt

import android.app.Application
import android.os.Environment
import androidx.multidex.MultiDex
import com.ciruy.b.heimerdinger.onion_view.manager.OnionDisposableManager
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
        //多dex操作
        MultiDex.install(this)
        //插件化换肤操作
        SkinManager.install(this)
        //disposable强迫症回收操作
        OnionDisposableManager.install(this)
    }
}