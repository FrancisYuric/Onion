package com.example.learnkt.util

import android.content.Context
import android.widget.Toast

object ToastUtil {

fun long(context:Context?,message:String?){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
    fun short(context:Context?,message:String?){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}