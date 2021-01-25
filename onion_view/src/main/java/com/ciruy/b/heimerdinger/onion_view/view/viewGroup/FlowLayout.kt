package com.ciruy.b.heimerdinger.onion_view.view.viewGroup

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

class FlowLayout : ViewGroup {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, style: Int) : super(context, attributeSet, style)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    }
}