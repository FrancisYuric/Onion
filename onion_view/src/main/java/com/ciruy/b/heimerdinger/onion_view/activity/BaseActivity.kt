package com.ciruy.b.heimerdinger.onion_view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(open var layout: Int?) : AppCompatActivity() {
    constructor() : this(null)

    open fun layout(): Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this.layout ?: layout()
        ?: error("activity must set init param or override layout()"))
    }

    override fun onStart() {
        super.onStart()
        initViews()
        initListeners()
        initData()
    }

    open fun initData() {

    }

    open fun initViews() {

    }

    fun hideActionBar() = supportActionBar?.hide()

    fun showActionBar() = supportActionBar?.show()

    open fun initListeners() {

    }
}