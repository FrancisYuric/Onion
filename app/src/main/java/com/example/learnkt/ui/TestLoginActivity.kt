package com.example.learnkt.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.learnkt.R
import com.example.learnkt.api.APIClient
import com.example.learnkt.api.WanAndroidAPI
import com.example.learnkt.bean.LoginResultEntity
import com.example.learnkt.bean.rx.LoadingAPIResultObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_user_login.*

class TestLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
        user_login_bt.setOnClickListener(onClickListener)
    }

    private val onClickListener: View.OnClickListener = View.OnClickListener {
        when (it.id) {
            username_tv.id -> {
            }
            password_tv.id -> {
            }
            user_login_bt.id -> {
                val userNameStr = username_tv.text.toString()
                val passwordStr = password_tv.text.toString()
                APIClient.instances.instanceRetrofit(WanAndroidAPI::class.java)
                    .loginAction(userNameStr, passwordStr)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : LoadingAPIResultObserver<LoginResultEntity>(this) {
                        override fun fail(errMes: Throwable) {

                        }

                        override fun success(target: LoginResultEntity?) {
                        }

                    })
            }


        }
    }


}