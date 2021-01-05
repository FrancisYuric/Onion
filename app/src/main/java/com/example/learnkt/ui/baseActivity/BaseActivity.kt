package com.example.learnkt.ui.baseActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity(){

    abstract fun layout():Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
        initListeners()
    }
    abstract fun initListeners()
}