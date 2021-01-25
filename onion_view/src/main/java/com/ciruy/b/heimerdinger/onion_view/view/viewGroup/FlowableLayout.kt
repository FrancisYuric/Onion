package com.ciruy.b.heimerdinger.onion_view.view.viewGroup

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import com.ciruy.b.heimerdinger.onion_view.view.*
import com.ciruy.onion_base.util.LogUtil
import kotlin.math.max

class FlowableLayout : ViewGroup {
    companion object {
        const val TAG = "FlowLayout"
        val mHorizontalSpacing = 16.dp2px()
        val mVerticalSpacing = 8.dp2px()
    }

    private val allLines = mutableListOf<MutableList<View>>()
    private val lineHeights = mutableListOf<Int>()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun clearMeasureParams() {
        allLines.clear()
        lineHeights.clear()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        clearMeasureParams()
        val selfWidth = widthMeasureSpec.specSize()
        val selfHeight = heightMeasureSpec.specSize()
        var lineViews = mutableListOf<View>()
        var lineWidthUsed = 0
        var lineHeight = 0
        var parentNeededWidth = 0
        var parentNeededHeight = 0
        children.filter { it.visibility != View.GONE }
                .withIndex()
                .forEach {
                    val childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingHorizontal, it.value.layoutParams.width)
                    val childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingVertical, it.value.layoutParams.height)
                    it.value.measure(childWidthMeasureSpec, childHeightMeasureSpec)

                    val childMeasuredWidth = it.value.measuredWidth
                    val childMeasuredHeight = it.value.measuredHeight

                    if (childMeasuredWidth + lineWidthUsed + mHorizontalSpacing > selfWidth) {
                        allLines.add(lineViews)
                        lineHeights.add(lineHeight)

                        parentNeededHeight += lineHeight + mVerticalSpacing
                        parentNeededWidth += lineWidthUsed + mHorizontalSpacing

                        lineViews = mutableListOf()
                        lineWidthUsed = 0
                        lineHeight = 0
                    }

                    lineViews.add(it.value)
                    lineWidthUsed += childMeasuredWidth + mHorizontalSpacing
                    lineHeight = max(lineHeight, childMeasuredHeight)
                    if (it.index == childCount - 1) {
                        allLines.add(lineViews)
                        lineHeights.add(lineHeight)
                        parentNeededHeight += lineHeight + mVerticalSpacing
                        parentNeededWidth = max(parentNeededWidth, lineWidthUsed + mHorizontalSpacing)
                    }
                }
        val widthMode = widthMeasureSpec.specMode()
        val heightMode = heightMeasureSpec.specMode()

        val realWidth = if (widthMode == MeasureSpec.EXACTLY) selfWidth else parentNeededWidth
        val realHeight = if (heightMode == MeasureSpec.EXACTLY) selfHeight else parentNeededHeight
        setMeasuredDimension(realWidth, realHeight)
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        LogUtil.e("flowLayout onLayout")
        val lineCount = allLines.size
        var curL = paddingLeft
        var curT = paddingTop
        for (i in 0 until lineCount) {
            val lineViews = allLines[i]
            val lineHeight = lineHeights[i]
            for (j in 0 until lineViews.size) {
                val view = lineViews[j]
                val left = curL
                val top = curT
                val right = left + view.measuredWidth
                val bottom = top + view.measuredHeight
                view.layout(left, top, right, bottom)
                curL = right + mHorizontalSpacing
            }
            curT += lineHeight + mVerticalSpacing
            curL = paddingLeft
        }
    }
}
