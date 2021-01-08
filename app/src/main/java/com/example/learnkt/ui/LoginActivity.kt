package com.example.learnkt.ui

import com.ciruy.b.heimerdinger.onion_view.textChanges
import com.example.learnkt.R
import com.example.learnkt.ui.baseActivity.BaseDisposableActivity
import com.example.learnkt.util.bind2ProgressDownload
import kotlinx.android.synthetic.main.login.*

class LoginActivity : BaseDisposableActivity() {
    override fun layout(): Int = R.layout.login
    override fun initViews() {
        super.initViews()
        et_username.textChanges()
                .subscribe{

                }
        et_password.textChanges()
                .subscribe()
    }
}