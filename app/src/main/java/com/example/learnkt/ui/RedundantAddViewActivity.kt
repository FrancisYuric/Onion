package com.example.learnkt.ui

import android.widget.TextView
import com.ciruy.b.heimerdinger.onion_view.activity.BaseActivity
import com.example.learnkt.R
import kotlinx.android.synthetic.main.activity_redunt.*

class RedundantAddViewActivity : BaseActivity() {
    override fun layout(): Int = R.layout.activity_redunt

    override fun initViews() {
        super.initViews()
        val textView = TextView(this)
        container.addView(textView)
        //添加子视图失败，已经绑定父视图的子视图无法重新绑定新的视图，除非解绑
//        container.addView(textView)
    }
}