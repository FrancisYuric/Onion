package com.ciruy.b.heimerdinger.onion_view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.viewpager.widget.PagerAdapter
import com.ciruy.b.heimerdinger.onion_view.R

class BadPagerAdapter(private val mContext: Context, private val mData: List<Map<String, Int>>) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = View.inflate(mContext, R.layout.item_list, null)
        val list = view.findViewById<ListView>(R.id.list)
        list.adapter = SimpleAdapter(container.context, mData, R.layout.item_base, arrayOf("key"), intArrayOf(R.id.iv))
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) = container.removeView(`object` as View?)

    override fun getCount(): Int = 5
}