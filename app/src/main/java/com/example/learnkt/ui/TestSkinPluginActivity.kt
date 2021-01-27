package com.example.learnkt.ui

import android.view.View
import com.ciruy.onion_plugin.SkinManager
import com.example.learnkt.R
import com.example.learnkt.ui.baseActivity.BaseDisposableActivity

class TestSkinPluginActivity : BaseDisposableActivity() {
    override fun layout() = R.layout.activity_test_skin_plugin
    fun change(view: View) = SkinManager.instance?.loadSkin("/data/data/com.example.learnkt/skin/skin-debug.apk")
    fun restore(view: View) = SkinManager.instance?.loadSkin(null)
}