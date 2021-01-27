package com.ciruy.onion_plugin.utils

import android.content.Context
import android.content.res.Resources
import android.text.TextUtils

class SkinResources(context: Context) {
    private val mAppResources: Resources = context.resources
    private var mSkinResources: Resources? = null
    private var mSkinPkgName: String? = ""
    private var isDefaultSkin = true

    companion object {
        private var instance: SkinResources? = null
        fun instance() = instance!!
        fun init(context: Context) {
            if (instance == null)
                synchronized(SkinResources::class) {
                    if (instance == null)
                        instance = SkinResources(context)
                }
        }
    }

    fun reset() {
        mSkinResources = null
        mSkinPkgName = ""
        isDefaultSkin = true
    }

    fun applySkin(resources: Resources?, pkgName: String?) {
        mSkinResources = resources
        mSkinPkgName = pkgName
        isDefaultSkin = TextUtils.isEmpty(pkgName) || resources == null
    }

    private fun getIdentifier(resId: Int) = if (isDefaultSkin) resId
    else mSkinResources?.getIdentifier(
            mAppResources.getResourceEntryName(resId),
            mAppResources.getResourceTypeName(resId), mSkinPkgName
    )

    fun getColor(resId: Int): Int = when {
        isDefaultSkin -> mAppResources.getColor(resId)
        getIdentifier(resId) == 0 -> mAppResources.getColor(resId)
        else -> mSkinResources!!.getColor(getIdentifier(resId)!!)
    }


    fun getColorStateList(resId: Int) = when {
        isDefaultSkin -> mAppResources.getColorStateList(resId)
        getIdentifier(resId) == 0 -> mAppResources.getColorStateList(resId)
        else -> mSkinResources!!.getColorStateList(getIdentifier(resId)!!)
    }

    fun getDrawable(resId: Int) = when {
        isDefaultSkin -> mAppResources.getDrawable(resId)
        resId.identifier == 0 -> mAppResources.getDrawable(resId)
        else -> mSkinResources?.getDrawable(resId.identifier)
    }

    fun getBackground(resId: Int): Any = when (mAppResources.getResourceTypeName(resId)) {
        "color" -> getColor(resId)
        else -> getDrawable(resId)!!
    }


    private val Int.identifier: Int
        get() = getIdentifier(this)!!
}