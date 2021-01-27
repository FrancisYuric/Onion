package com.ciruy.onion_plugin.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import com.ciruy.onion_plugin.R

class SkinThemeUtils {
    companion object {
        private val APPCOMPAT_COLOR_PRIMARY_DARK_ATTRS = intArrayOf(R.attr.colorPrimaryDark)
        private val STATUS_BAR_COLOR_ATTRS: IntArray = intArrayOf(
            android.R.attr.statusBarColor,
            android.R.attr.navigationBarColor
        )

        fun getResId(
            context: Context,
            attrs: IntArray
        ): Array<Int> {
            val a = context.obtainStyledAttributes(attrs)
            val resIds = Array(attrs.size) {
                a.getResourceId(it, 0)
            }
            a.recycle()
            return resIds
        }

        fun updateStatusBarColor(activity: Activity) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
                return
            val resIds = getResId(activity, STATUS_BAR_COLOR_ATTRS)
            val statusBarColorResId = resIds[0]
            val navigationBarColor = resIds[1]

            if (statusBarColorResId != 0) {
                val color = SkinResources.instance().getColor(statusBarColorResId)
                activity.window.statusBarColor = color!!
            } else {
                val colorPrimaryDarkResId =
                    getResId(activity, APPCOMPAT_COLOR_PRIMARY_DARK_ATTRS)[0]
                if (colorPrimaryDarkResId != 0) {
                    val color = SkinResources.instance().getColor(colorPrimaryDarkResId)
                    activity.window.statusBarColor = color!!
                }
            }
            if (navigationBarColor != 0) {
                val color = SkinResources.instance().getColor(navigationBarColor)
                activity.window.navigationBarColor = color!!
            }
        }
    }
}