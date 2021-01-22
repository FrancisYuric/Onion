package com.ciruy.b.heimerdinger.onion_view.transform

import android.view.View
import androidx.viewpager.widget.ViewPager

object PageTransform : ViewPager.PageTransformer {
    private const val DEFAULT_MIN_ALPHA = 0.3f
    private const val DEFAULT_MAX_ROTATE = 15.0f

    private const val mMinAlpha = DEFAULT_MIN_ALPHA
    private const val mMaxRotate = DEFAULT_MAX_ROTATE
    override fun transformPage(view: View, position: Float) {
        if (position < -1) {
            view.alpha = mMinAlpha
            view.rotation = mMaxRotate * -1
            view.pivotX = view.width.toFloat()
            view.pivotY = view.height.toFloat()
        } else if (position <= 1) {
            if (position < 0) {
                val factor = mMinAlpha + (1 - mMinAlpha) * (1 + position)
                view.alpha = factor
                view.rotation = mMaxRotate * position
                view.pivotX = view.width * 0.5f * (1 - position)
                view.pivotY = view.height.toFloat()
            } else {
                val factor = mMinAlpha + (1 - mMinAlpha) * (1 - position)
                view.alpha = factor
                view.rotation = mMaxRotate * position
                view.pivotX = view.width * 0.5f * (1 - position)
                view.pivotY = view.height.toFloat()
            }
        } else {
            view.alpha = mMinAlpha
            view.rotation = mMaxRotate
            view.pivotX = 0f
            view.pivotY = view.height.toFloat()
        }
    }
}