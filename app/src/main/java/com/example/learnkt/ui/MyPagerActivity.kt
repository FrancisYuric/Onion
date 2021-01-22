package com.example.learnkt.ui

import android.annotation.SuppressLint
import android.os.Message
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.ciruy.b.heimerdinger.onion_view.activity.BaseActivity
import com.ciruy.b.heimerdinger.onion_view.adapter.MyPagerAdapter
import com.ciruy.b.heimerdinger.onion_view.transform.PageTransform
import com.example.learnkt.R
import kotlinx.android.synthetic.main.activity_my_page.*
import java.util.*

class MyPagerActivity : BaseActivity() {
    override fun layout() = R.layout.activity_my_page
    private val images: MutableList<Int> = mutableListOf()
    var index: Int = 0
    var preIndex: Int = 0
    private val timer = Timer()
    var isContinue = true
    val onPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            index = position
            setCurrentDot(index % images.size)
        }
    }

    fun setCurrentDot(i: Int) {
        if (radio_group.getChildAt(i) != null) radio_group.getChildAt(i).isEnabled = false
        if (radio_group.getChildAt(preIndex) != null) {
            radio_group.getChildAt(preIndex).isEnabled = true
            preIndex = i
        }
    }

    override fun initData() {
        images.addAll(listOf(R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.girl5))
        val pagerAdapter = MyPagerAdapter(images, this)
        view_Pager.pageMargin = 30
        view_Pager.offscreenPageLimit = 3
        view_Pager.adapter = pagerAdapter
        view_Pager.addOnPageChangeListener(onPageChangeListener)
        view_Pager.setPageTransformer(true, PageTransform)
        initRadioButton(images.size)
        timer.schedule(object : TimerTask() {
            override fun run() {
                if (isContinue) handler.sendEmptyMessage(1)
            }
        }, 2000, 2000)
    }

    val handler = @SuppressLint("HandlerLeak")
    object : android.os.Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1 -> {
                    index++
                    view_Pager.currentItem = index
                }
            }
        }
    }

    private fun initRadioButton(length: Int) {
        for (i in 0 until length) {
            val imageView = ImageView(this)
            imageView.setImageResource(R.drawable.rg_selector)
            imageView.setPadding(20, 0, 0, 0)
            radio_group.addView(imageView, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            radio_group.getChildAt(0).isEnabled = false
        }
    }
}