package com.ciruy.onion_plugin

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.ArrayMap
import android.view.LayoutInflater
import androidx.core.view.LayoutInflaterCompat
import com.ciruy.onion_base.constant.Field
import com.ciruy.onion_base.constant.staticSet
import com.ciruy.onion_plugin.utils.SkinThemeUtils
import java.util.*

/**
 * 被订阅事件
 * @see com.ciruy.onion_plugin.SkinManager
 */
class ApplicationActivityLifeCycle(private var observable: Observable) :
        Application.ActivityLifecycleCallbacks {

    private val mLayoutInflaterFactories by lazy { ArrayMap<Activity, SkinLayoutInflaterFactory>() }
    override fun onActivitySaveInstanceState(tActivity: Activity, tBundle: Bundle) = Unit
    override fun onActivityCreated(tActivity: Activity, savedInstanceState: Bundle?) {
        //更新状态栏颜色
        SkinThemeUtils.updateStatusBarColor(tActivity)
        val layoutInflater = tActivity.layoutInflater
        //反射来将该标记来设置为false,因为在createViewByTag方法中，只有这个参数为false，才会尝试基于
        //factory2来生成视图
        layoutInflater.staticSet(LayoutInflater::class.java, Field.LayoutInflater_mFactorySet, false)
        val skinLayoutInflaterFactory = SkinLayoutInflaterFactory(tActivity)
        LayoutInflaterCompat.setFactory2(layoutInflater, skinLayoutInflaterFactory)
        mLayoutInflaterFactories[tActivity] = skinLayoutInflaterFactory
        //为被订阅者设置订阅者
        observable.addObserver(skinLayoutInflaterFactory)
    }

    override fun onActivityStarted(tActivity: Activity) = Unit
    override fun onActivityResumed(tActivity: Activity) = Unit
    override fun onActivityPaused(tActivity: Activity) = Unit
    override fun onActivityStopped(tActivity: Activity) = Unit

    //取消订阅
    override fun onActivityDestroyed(tActivity: Activity) = observable.deleteObserver(mLayoutInflaterFactories.remove(tActivity))
}