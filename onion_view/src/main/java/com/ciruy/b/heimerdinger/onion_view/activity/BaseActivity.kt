package com.ciruy.b.heimerdinger.onion_view.activity

import android.os.Bundle
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

    fun hideActionBar() = supportActionBar?.hide()

    fun showActionBar() = supportActionBar?.show()

    open fun initListeners() {

    }
}