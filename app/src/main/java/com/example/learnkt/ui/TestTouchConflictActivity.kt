package com.example.learnkt.ui

import com.ciruy.b.heimerdinger.onion_view.activity.BaseActivity
import com.ciruy.b.heimerdinger.onion_view.adapter.BadPagerAdapter
import com.example.learnkt.R
import kotlinx.android.synthetic.main.activity_test_touch_conflict.*

class TestTouchConflictActivity : BaseActivity(R.layout.activity_test_touch_conflict) {
    private val iv = arrayOf(R.mipmap.iv_0, R.mipmap.iv_1, R.mipmap.iv_2, R.mipmap.iv_3,
            R.mipmap.iv_4, R.mipmap.iv_5, R.mipmap.iv_6, R.mipmap.iv_7, R.mipmap.iv_8)

    override fun initViews() {
        super.initViews()
        val strings = mutableListOf<Map<String, Int>>()
        var map: Map<String, Int>
        for (i in 0 until 20) {
            map = hashMapOf()
            map["key"] = iv[i % 9]
            strings.add(map)
        }
        val adapter = BadPagerAdapter(this, strings)
        viewpager.adapter = adapter
    }
}