package com.ciruy.onion_plugin.view

import android.content.Context
import android.util.AttributeSet
import com.ciruy.onion_plugin.R
import com.ciruy.onion_plugin.SkinViewSupport
import com.ciruy.onion_plugin.utils.SkinResources
import com.google.android.material.tabs.TabLayout

class PTabLayout : TabLayout, SkinViewSupport {
    private var tabIndicatorColorResId: Int
    private var tabTextColorResId: Int

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.TabLayout, defStyleAttr, 0)
        tabIndicatorColorResId = a.getResourceId(R.styleable.TabLayout_tabIndicatorColor, 0)
        tabTextColorResId = a.getResourceId(R.styleable.TabLayout_tabTextColor, 0)
        a.recycle()
    }

    override fun applySkin() {
        if (tabIndicatorColorResId != 0) setSelectedTabIndicator(SkinResources.instance!!.getColor(tabIndicatorColorResId))
        if (tabTextColorResId != 0) tabTextColors = SkinResources.instance!!.getColorStateList(tabTextColorResId)
    }
}