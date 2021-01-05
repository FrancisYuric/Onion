package com.example.learnkt.util

import android.util.Log

object LogUtil {
    val TAG = "Ciruy.B.Heimerdinger"
    fun e(message: String) {
        Log.e(TAG, message)
    }

    fun i(message: String) {
        Log.i(TAG, message)
    }

    fun d(message: String) {
        Log.i(TAG, message)
    }
}