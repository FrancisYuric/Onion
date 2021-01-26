package com.ciruy.onion_plugin

import android.app.Application
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import android.text.TextUtils
import com.ciruy.onion_plugin.utils.SkinResources
import java.util.*

class SkinManager(private val mApplication: Application) : Observable() {
    private val skinActivityLifecycle: ApplicationActivityLifeCycle

    init {
        SkinPreference.init(mApplication)
        SkinResources.init(mApplication)
        skinActivityLifecycle = ApplicationActivityLifeCycle(this)
        mApplication.registerActivityLifecycleCallbacks(skinActivityLifecycle)
        loadSkin(SkinPreference.instance!!.getSkin()!!)
    }

    companion object {
        @Volatile
        var instance: SkinManager? = null

        fun getInstance(mApplication: Application) = run {
            if (instance == null) {
                synchronized(SkinManager::class.java)
                {
                    if (instance == null) {
                        instance = SkinManager(mApplication)
                    }
                }
            }
            instance
        }
    }

    private fun loadSkin(skinPath: String) {
        when {
            TextUtils.isEmpty(skinPath) -> {
                SkinPreference.instance?.reset()
                SkinResources.instance?.reset()
            }
            else -> {
                val appResources = mApplication.resources
                val assetManager = AssetManager::class.java.newInstance()
                val addAssetPath = AssetManager::class.java.getMethod(
                    "addAssetPath",
                    String::class.java
                )
                addAssetPath.invoke(assetManager, skinPath)

                val skinResources = Resources(
                    assetManager, appResources.displayMetrics,
                    appResources.configuration
                )
                val mPm = mApplication.packageManager
                val info = mPm.getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES)

                SkinResources.instance?.applySkin(skinResources, info?.packageName)
                SkinPreference.instance?.setSkin(skinPath)
            }
        }
        setChanged()
        notifyObservers(null)
    }

}