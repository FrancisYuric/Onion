package com.example.learnkt.modules.example

import android.widget.TextView
import com.example.learnkt.bean.supcon_login
import com.example.learnkt.inter.IModel

object TestModel : IModel<TestModel>, ITest {
    override fun login(url: String, username: String, password: String) = supcon_login(url, username, password)

    override fun method1(textView: TextView) = Unit

    override fun method3() = Unit
}