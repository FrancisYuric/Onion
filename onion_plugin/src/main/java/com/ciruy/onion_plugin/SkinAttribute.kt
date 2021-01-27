package com.ciruy.onion_plugin

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.view.ViewCompat
import com.ciruy.b.heimerdinger.onion_view.view.asImageView
import com.ciruy.b.heimerdinger.onion_view.view.asTextView
import com.ciruy.onion_plugin.utils.SkinResources
import com.ciruy.onion_plugin.utils.SkinThemeUtils

class SkinAttribute {
    companion object {
        val mAttributes = arrayListOf(
                "background",
                "src",
                "textColor",
                "drawableLeft",
                "drawableTop",
                "drawableRight",
                "drawableBottom")
    }

    private val mSkinViews = arrayListOf<SkinView>()

    fun look(view: View, attrs: AttributeSet) {
        val mSkinPairs = arrayListOf<SkinPair>()
        Array<Pair<String, String>>(attrs.attributeCount) {
            Pair(attrs.getAttributeName(it), attrs.getAttributeValue(it))
        }.filter {
            mAttributes.contains(it.first) && !it.second.startsWith("#")
        }.map {
            SkinPair(it.first, when {
                it.second.startsWith("?") -> SkinThemeUtils.getResId(view.context,
                        intArrayOf(it.second.substring(1).toInt()))[0]
                else -> it.second.substring(1).toInt()
            })
        }.onEach {
            mSkinPairs.add(it)
        }
        if (mSkinPairs.isNotEmpty() || view is SkinViewSupport) {
            val skinView = SkinView(view, mSkinPairs)
            skinView.applySkin()
            mSkinViews.add(skinView)
        }
    }

    fun applySkin() {
        mSkinViews.onEach { it.applySkin() }
    }

}

class SkinView(private val view: View, private val skinPairs: List<SkinPair>) {
    fun applySkin() {
        applySkinSupport()
        skinPairs.onEach {
            var left: Drawable? = null
            var top: Drawable? = null
            var right: Drawable? = null
            var bottom: Drawable? = null
            when (it.attributeName) {
                "background" -> when (val background = SkinResources.instance().getBackground(it.resId)) {
                    is Int -> view.setBackgroundColor(background)
                    is Drawable ->
                        ViewCompat.setBackground(view, background)
                    else -> throw IllegalStateException("wrong SkinView background class type")
                }
                "src" -> when (val background = SkinResources.instance().getBackground(it.resId)) {
                    is Int -> view.asImageView().setImageDrawable(ColorDrawable(background))
                    is Drawable -> view.asImageView().setImageDrawable(background)
                    else -> throw java.lang.IllegalStateException("wrong SkinView background class Type")
                }
                "textColor" -> view.asTextView().setTextColor(SkinResources.instance().getColorStateList(it.resId))
                "drawableLeft" -> left = SkinResources.instance().getDrawable(it.resId)
                "drawableTop" -> top = SkinResources.instance().getDrawable(it.resId)
                "drawableRight" -> right = SkinResources.instance().getDrawable(it.resId)
                "drawableBottom" -> bottom = SkinResources.instance().getDrawable(it.resId)
            }
            if (left != null || right != null || top != null || bottom != null)
                view.asTextView().setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
        }
    }

    private fun applySkinSupport() = if (view is SkinViewSupport) view.applySkin() else Unit
}

data class SkinPair(val attributeName: String, val resId: Int)