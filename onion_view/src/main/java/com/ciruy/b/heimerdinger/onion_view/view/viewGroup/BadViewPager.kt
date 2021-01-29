package com.ciruy.b.heimerdinger.onion_view.view.viewGroup

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

class BadViewPager : ViewPager {
    lateinit var xyPair: Pair<Int, Int>

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        val x = event!!.x.toInt()
        val y = event.y.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> xyPair = Pair(x, y)
            MotionEvent.ACTION_MOVE -> if (abs(x - xyPair.first) > abs(y - xyPair.second)) return true
        }
        return super.onInterceptTouchEvent(event)
    }
}