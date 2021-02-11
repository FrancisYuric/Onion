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
        initViews()
        initListeners()
        initData()
    }

    open fun initData() = Unit

    open fun initViews() = Unit

    open fun initListeners() = Unit

    fun hideActionBar() = supportActionBar?.hide()

    fun showActionBar() = supportActionBar?.show()

}