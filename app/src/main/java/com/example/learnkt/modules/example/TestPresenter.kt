package com.example.learnkt.modules.example

import android.widget.TextView
import com.example.learnkt.bean.legalUrl
import com.example.learnkt.bean.supcon.LoginEntity
import com.example.learnkt.inter.IPresenter
import com.ciruy.onion_base.util.ToastUtil
import io.reactivex.Flowable

/**
 * 校验的相关操作交给Presenter，然后业务执行的操作交给model
 */
object TestPresenter : IPresenter<TestModel, TestPresenter>(), ITest {
    override fun login(url: String, username: String, password: String): Flowable<LoginEntity> =
            if (!legalUrl(url))
                LoginEntity.onErrorReturn("非法的URL地址")
            else if (url.isEmpty())
                LoginEntity.onErrorReturn("url地址不能为空")
            else if (password.isEmpty())
                LoginEntity.onErrorReturn("密码不能为空")
            else if (username.isEmpty())
                LoginEntity.onErrorReturn("账号不能为空")
            else model.login(url, username, password)

    override fun method1(textView: TextView) =
            if (textView.text.isEmpty()) ToastUtil.short(textView.context, "内容不能空!")
            else model.method1(textView)

    override fun model(): TestModel = TestModel

    override fun method3() = Unit
}