package com.example.learnkt

import android.app.Application
import androidx.multidex.MultiDex
import com.ciruy.b.heimerdinger.onion_view.manager.OnionDisposableManager
import com.ciruy.heimerdinger.onion_net.manager.OnionDownloadManager
import com.ciruy.onion_plugin.SkinManager

class CiruyApplication : Application() {

    companion object {
        private var instance: CiruyApplication? = null
        fun instance() = instance!!
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        //多dex操作
        MultiDex.install(this)
        //插件化换肤操作
        SkinManager.install(this)
        //disposable强迫症回收操作
        OnionDisposableManager.install(this)
        //下载管理器初始化
        OnionDownloadManager.instance().install(this)
    }
}