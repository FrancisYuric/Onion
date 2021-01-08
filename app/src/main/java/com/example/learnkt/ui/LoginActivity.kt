package com.example.learnkt.ui

import com.example.learnkt.R
import com.example.learnkt.ui.baseActivity.BaseDisposableActivity
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.login.*

class LoginActivity : BaseDisposableActivity() {
    override fun layout(): Int = R.layout.login
    override fun initViews() {
        super.initViews()
        et_username.textChanges(Consumer {})
        et_password.textChanges(Consumer {})
    }
}