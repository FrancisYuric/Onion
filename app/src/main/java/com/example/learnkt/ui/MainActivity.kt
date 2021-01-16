package com.example.learnkt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ciruy.b.heimerdinger.annotation.BuilderClass
import com.example.learnkt.R
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

@BuilderClass
class MainActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

