package com.ciruy.b.heimerdinger.onion_view.view

import android.view.View
import android.view.View.getDefaultSize
import android.widget.ImageView
import android.widget.TextView

fun <T:View>T.asImageView()=this as ImageView
fun <T:View>T.asTextView()=this as TextView
