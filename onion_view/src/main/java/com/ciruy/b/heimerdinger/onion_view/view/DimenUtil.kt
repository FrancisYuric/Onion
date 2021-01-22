package com.ciruy.b.heimerdinger.onion_view.view

import android.content.res.Resources
import android.util.TypedValue
import android.view.View

fun Int.dp2px() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics).toInt()

fun Int.specSize() = View.MeasureSpec.getSize(this)
fun Int.specMode() = View.MeasureSpec.getMode(this)

val <T : View> T.paddingHorizontal
    get() = this.paddingLeft + this.paddingRight
val <T : View>T.paddingVertical
    get() = this.paddingBottom + this.paddingTop

