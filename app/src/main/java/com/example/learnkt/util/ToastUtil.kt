package com.example.learnkt.util

import android.content.Context
import android.widget.Toast

class ToastUtil {
    fun long(context:Context?,message:String?){
        Toast.makeText(context, message, Toast.LENGTH_LONG)
    }
    fun short(context:Context?,message:String?){
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
    }
}