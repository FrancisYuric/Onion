package com.ciruy.onion_base.util

import android.util.Log

object LogUtil {
    val TAG = "Ciruy.B.Heimerdinger"
    fun e(message: String) = Log.e(TAG, message)
    fun e(tag:String,message: String) = Log.e(tag, message)
    fun i(message: String) = Log.i(TAG, message)
    fun i(tag: String,message: String) = Log.i(tag, message)
    fun d(message: String) = Log.i(TAG, message)
    fun d(tag:String,message: String) = Log.d(tag, message)
}