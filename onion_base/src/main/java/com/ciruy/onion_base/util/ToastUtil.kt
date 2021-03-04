package com.ciruy.onion_base.util

import android.content.Context
import android.widget.Toast
import java.time.Duration

object ToastUtil {

    fun duration(context: Context?, message: String?, duration: Int){
        Toast.makeText(context, message,duration).show()
    }
    fun long(context: Context?, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun short(context: Context?, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}