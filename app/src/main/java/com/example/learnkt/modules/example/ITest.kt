package com.example.learnkt.modules.example

import android.widget.TextView
import ciruy.b.heimerdinger.annotation.MVP
import com.example.learnkt.bean.supcon.LoginEntity
import io.reactivex.Flowable

@MVP(model = Nothing::class,
        view = Nothing::class,
        presenter = Nothing::class)
interface ITest {
    fun method1(textView:TextView)
    fun method2(url:String, username:String,password:String):Flowable<LoginEntity>
    fun method3()
}