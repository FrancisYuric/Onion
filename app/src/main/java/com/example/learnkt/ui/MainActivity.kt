package com.example.learnkt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ciruy.b.heimerdinger.annotation.BuilderClass
import com.example.learnkt.R

@BuilderClass
class MainActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
