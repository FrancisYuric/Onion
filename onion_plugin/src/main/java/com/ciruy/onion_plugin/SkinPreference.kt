package com.ciruy.onion_plugin

import android.content.Context
import android.content.SharedPreferences

class SkinPreference(context: Context) {
    companion object {
        val SKIN_SHARED = "skins"
        val KEY_SKI_PATH = "skin-path"
        @Volatile
        var instance: SkinPreference? = null

        fun init(context: Context) {
            if (instance == null)
                synchronized(SkinPreference::class) {
                    if (instance == null)
                        instance = SkinPreference(context.applicationContext)
                }
        }
    }

    lateinit var mPref: SharedPreferences

    init {
        mPref = context.getSharedPreferences(SKIN_SHARED, Context.MODE_PRIVATE)
    }

    fun setSkin(skinPath:String) = mPref.edit().remove(KEY_SKI_PATH).apply()
    fun getSkin() = mPref.getString(KEY_SKI_PATH, null)

    fun reset(){
        mPref.edit().remove(KEY_SKI_PATH).apply()
    }
}