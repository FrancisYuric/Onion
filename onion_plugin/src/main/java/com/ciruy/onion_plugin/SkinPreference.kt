package com.ciruy.onion_plugin

import android.content.Context
import android.content.SharedPreferences

class SkinPreference(context: Context) {
    companion object {
        const val SKIN_SHARED = "skins"
        const val KEY_SKIN_PATH = "skin-path"
        @Volatile
        var instance: SkinPreference? = null

        fun instance() = instance!!
        fun init(context: Context) {
            if (instance == null)
                synchronized(SkinPreference::class) {
                    if (instance == null)
                        instance = SkinPreference(context.applicationContext)
                }
        }
    }

    var mPref: SharedPreferences

    init {
        mPref = context.getSharedPreferences(SKIN_SHARED, Context.MODE_PRIVATE)
    }

    fun setSkin(skinPath: String) = mPref.edit().putString(KEY_SKIN_PATH, skinPath).commit()
//    fun removeSkin() = mPref.edit().remove(KEY_SKIN_PATH).apply()

    fun getSkin() = mPref.getString(KEY_SKIN_PATH, null)

    fun reset() {
        mPref.edit().remove(KEY_SKIN_PATH).apply()
    }
}