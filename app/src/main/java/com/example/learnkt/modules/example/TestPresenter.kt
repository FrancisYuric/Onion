package com.example.learnkt.modules.example

import android.widget.TextView
import com.example.learnkt.bean.legalUrl
import com.example.learnkt.bean.supcon.LoginEntity
import com.example.learnkt.inter.IPresenter
import com.example.learnkt.util.ToastUtil
import io.reactivex.Flowable

/**
 * 校验的相关操作交给Presenter，然后业务执行的操作交给model
 */
object TestPresenter : IPresenter<TestModel, TestPresenter>(), ITest {
    override fun login(url: String, username: String, password: String) =
            if (legalUrl(url) && url.isNotEmpty() && password.isNotEmpty() && username.isNotEmpty())
                model.login(url, username, password)
            else Flowable.empty<LoginEntity>()

    override fun method1(textView: TextView) =
            if (textView.text.isEmpty()) ToastUtil.short(textView.context, "内容不能空!")
            else model.method1(textView)

    override fun model(): TestModel = TestModel

    override fun method3() = Unit
}