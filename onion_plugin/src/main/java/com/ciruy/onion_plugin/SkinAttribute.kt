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
        //设置我们所感兴趣的属性
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

    /**
     * 我们在factory2的onCreateView方法中添加了对于所创建的每一个视图
     * @see com.ciruy.onion_plugin.SkinLayoutInflaterFactory.onCreateView(android.view.View, java.lang.String, android.content.Context, android.util.AttributeSet)
     * 可以操作的视图分为两种，一种是官方的一些视图，对于这些视图，我们的可控性其实非常高，只需要对于特定的一些属性进行控制就行了
     * 但是一些我们自定义的视图，如果也按照这样的逻辑进行筛选的话，可能会使逻辑过于复杂和冗余，所以这些视图我们就特事特办，
     * 让他们继承SkinViewSupport接口加以区分即可
     */
    fun look(view: View, attrs: AttributeSet) {
        val mSkinPairs = arrayListOf<SkinPair>()
        Array<Pair<String, String>>(attrs.attributeCount) {
            Pair(attrs.getAttributeName(it), attrs.getAttributeValue(it))
        }.filter {//如果属性是我们所感兴趣的属性，并且并非通过硬编码指定，执行后续操作
            mAttributes.contains(it.first) && !it.second.startsWith("#")
        }.map {//pair的首位是属性的名称，次位是
            SkinPair(it.first, when {
                //可能是直接调用系统直接提供的颜色或者是图片，android:background="?android:attr/windowBackground"
                it.second.startsWith("?") -> SkinThemeUtils.getResId(view.context,
                        intArrayOf(it.second.substring(1).toInt()))[0]
                //或者是封装好的颜色以及图片信息
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