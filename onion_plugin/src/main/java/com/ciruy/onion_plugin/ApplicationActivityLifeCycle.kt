package com.ciruy.onion_plugin

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.ArrayMap
import android.view.LayoutInflater
import androidx.core.view.LayoutInflaterCompat
import com.ciruy.onion_base.constant.Field
import com.ciruy.onion_base.constant.set
import com.ciruy.onion_plugin.utils.SkinThemeUtils
import java.util.*

class ApplicationActivityLifeCycle(var observable: Observable) : Application.ActivityLifecycleCallbacks {

    private val mLayoutInflaterFactories by lazy { ArrayMap<Activity, SkinLayoutInflaterFactory>() }
    override fun onActivitySaveInstanceState(tActivity: Activity, tBundle: Bundle) = Unit
    override fun onActivityCreated(tActivity: Activity, savedInstanceState: Bundle?) {
        //更新状态栏颜色
        SkinThemeUtils.updateStatusBarColor(tActivity)
        val layoutInflater = tActivity.layoutInflater
        layoutInflater.set(LayoutInflater::class.java, Field.LayoutInflater_mFactorySet, false)
        val skinLayoutInflaterFactory = SkinLayoutInflaterFactory(tActivity)
        LayoutInflaterCompat.setFactory2(layoutInflater, skinLayoutInflaterFactory)
        mLayoutInflaterFactories[tActivity] = skinLayoutInflaterFactory
        observable.addObserver(skinLayoutInflaterFactory)
    }

    override fun onActivityStarted(tActivity: Activity) = Unit
    override fun onActivityResumed(tActivity: Activity) = Unit
    override fun onActivityPaused(tActivity: Activity) = Unit
    override fun onActivityStopped(tActivity: Activity) = Unit
    override fun onActivityDestroyed(tActivity: Activity) {
        val observer = mLayoutInflaterFactories.remove(tActivity)
        SkinManager.instance().deleteObserver(observer)
    }
}