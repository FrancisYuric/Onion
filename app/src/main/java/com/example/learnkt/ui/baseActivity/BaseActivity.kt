package com.example.learnkt.ui.baseActivity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun layout(): Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
    }

    override fun onStart() {
        super.onStart()
        initViews()
        initListeners()
    }
    open fun initViews() {

    }

    open fun initListeners() {

    }
}