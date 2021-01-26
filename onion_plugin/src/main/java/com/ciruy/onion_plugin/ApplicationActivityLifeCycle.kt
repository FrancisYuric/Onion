package com.ciruy.onion_plugin

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.ArrayMap
import android.view.LayoutInflater
import androidx.core.view.LayoutInflaterCompat
import com.ciruy.onion_plugin.utils.SkinThemeUtils
import java.util.*

class ApplicationActivityLifeCycle(var observable: Observable) : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(p0: Activity) {}

    override fun onActivityStarted(p0: Activity) {}
    override fun onActivityDestroyed(activity: Activity) {
        val observer = mLayoutInflaterFactories.remove(activity)
        SkinManager.instance?.deleteObserver(observer)
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}
    override fun onActivityStopped(p0: Activity) {}

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        SkinThemeUtils.updateStatusBarColor(activity)
        val layoutInflater = activity.layoutInflater
        val field = LayoutInflater::class.java.getDeclaredField("mFactorySet")
        field.isAccessible = true
        field.setBoolean(layoutInflater, false)
        val skinLayoutInflaterFactory = SkinLayoutInflaterFactory(activity)
        LayoutInflaterCompat.setFactory2(layoutInflater, skinLayoutInflaterFactory)
        mLayoutInflaterFactories[activity] = skinLayoutInflaterFactory
        observable.addObserver(skinLayoutInflaterFactory)
    }

    override fun onActivityResumed(p0: Activity) {}

    private val mLayoutInflaterFactories: ArrayMap<Activity, SkinLayoutInflaterFactory> = ArrayMap()

}