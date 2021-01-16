package com.ciruy.b.heimerdinger.onion_view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    abstract fun layoutId(): Int
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            layoutInflater.inflate(layoutId(), null)
                    ?: super.onCreateView(inflater, container, savedInstanceState)

}