package com.ciruy.b.heimerdinger.onion_view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.ciruy.b.heimerdinger.onion_view.R

class MyPagerAdapter(private val mImages: List<Int>, val context: Context) : PagerAdapter() {
    companion object {
        const val TAG = "MyPagerAdapter"
    }

    override fun getCount(): Int = Int.MAX_VALUE

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val actualPos = position%mImages.size
        val view = LayoutInflater.from(context).inflate(R.layout.linear_item, container,false)
        val textView = view.findViewById<TextView>(R.id.tv)
        textView.text = actualPos.toString()
        val imageView = view.findViewById<ImageView>(R.id.iv_image)
        imageView.setImageDrawable(context.getDrawable(mImages[actualPos]))
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}