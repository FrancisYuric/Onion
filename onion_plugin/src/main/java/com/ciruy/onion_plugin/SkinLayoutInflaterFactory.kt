package com.ciruy.onion_plugin

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.ciruy.b.heimerdinger.onion.createIfNull
import com.ciruy.b.heimerdinger.onion.doIfNotNull
import com.ciruy.onion_base.util.LogUtil
import com.ciruy.onion_plugin.utils.SkinThemeUtils
import java.lang.reflect.Constructor
import java.util.*

class SkinLayoutInflaterFactory(val activity: Activity) : LayoutInflater.Factory2, Observer {

    private val skinAttribute by lazy { SkinAttribute() }

    companion object {
        val mClassPrefixList = arrayListOf("android.widget.",
                "android.webkit.",
                "android.app.",
                "android.view.")
        val mConstructorMap = hashMapOf<String, Constructor<out View>>()

        private val mConstructorSignature = arrayOf(Context::class.java, AttributeSet::class.java)
    }

    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet) =
            createSDKView(name, context, attrs)
                    .createIfNull { createView(name, context, attrs) }
                    .doIfNotNull { skinAttribute.look(it!!, attrs) }

    private fun createSDKView(name: String, context: Context, attrs: AttributeSet): View? =
            if (name.contains('.')) null
            else mClassPrefixList.mapNotNull { createView(it + name, context, attrs) }
                    .getOrNull(0)

    private fun createView(name: String, context: Context, attrs: AttributeSet): View? =
            findConstructor(context, name)?.newInstance(context, attrs)


    private fun findConstructor(context: Context, name: String): Constructor<out View>? {
        when (mConstructorMap[name]) {
            null -> try {
                mConstructorMap[name] =
                        context.classLoader.loadClass(name).asSubclass(View::class.java).getConstructor(*mConstructorSignature)
            } catch (e: Exception) {
                LogUtil.e("constructor not found")
            }
        }
        return mConstructorMap[name]
    }

    /**
     * 观察者模式接收到被观察者更新的回调
     */
    override fun update(p0: Observable?, p1: Any?) {
        SkinThemeUtils.updateStatusBarColor(activity)
        skinAttribute.applySkin()
    }

    override fun onCreateView(p0: String, p1: Context, p2: AttributeSet): Nothing? = null
}