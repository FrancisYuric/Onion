package com.example.learnkt.modules.example

import android.widget.TextView
import com.example.learnkt.bean.Constant
import com.example.learnkt.bean.supcon.LoginEntity
import com.example.learnkt.inter.IPresenter
import com.example.learnkt.util.LogUtil
import com.example.learnkt.util.ToastUtil
import io.reactivex.Flowable
import java.util.regex.Pattern

/**
 * 校验的相关操作交给Presenter，然后业务执行的操作交给model
 */
object TestPresenter : IPresenter<TestModel, TestPresenter>(), ITest {
    override fun method2(url: String, username: String, password: String): Flowable<LoginEntity> =
            if (Pattern.compile(Constant.Pattern.URL_PATTERN).matcher(url).matches() &&
                    url.isNotEmpty() && password.isNotEmpty() && username.isNotEmpty())
                model.method2(url, username, password)
            else {
                LogUtil.e("ciruy")
                Flowable.empty<LoginEntity>()
            }

    override fun method1(textView: TextView) {
        if (textView.text.isEmpty()) ToastUtil.short(textView.context, "内容不能空!")
        else model.method1(textView)
    }

    override fun model(): TestModel = TestModel

    override fun method3() {
    }
}