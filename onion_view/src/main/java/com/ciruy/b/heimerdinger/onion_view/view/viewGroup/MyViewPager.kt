package com.ciruy.b.heimerdinger.onion_view.view.viewGroup

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager
import com.ciruy.b.heimerdinger.onion_view.view.childList
class MyViewPager : ViewPager {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var height = 0
        childList.onEach { childView ->
            val lp = childView.layoutParams
            val childWidthSpec = getChildMeasureSpec(widthMeasureSpec, 0, lp.width)
            val childHeightSpec = getChildMeasureSpec(heightMeasureSpec, 0, lp.height)
            childView.measure(childWidthSpec, childHeightSpec)
            val h = childView.measuredHeight
            if (h > height) height = h
        }
        val newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        setMeasuredDimension(widthMeasureSpec, newHeightMeasureSpec)
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)
    }
}