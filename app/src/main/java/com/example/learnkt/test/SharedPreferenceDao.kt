package com.example.learnkt.test

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceDao(private val sharedPreferences: SharedPreferences) {

    constructor(context: Context) : this(context.getSharedPreferences("config", Context.MODE_PRIVATE))

    fun put(key: String, value: String) = sharedPreferences.edit().putString(key, value).commit()
    fun get(key: String) = sharedPreferences.getString(key, null)
}