package com.example.learnkt.ui.fragment

import com.example.learnkt.R
import com.example.learnkt.bean.NetConstant
import kotlinx.android.synthetic.main.fragment_test.*

class TestFragment : BaseDisposableFragment() {
    override fun layoutId(): Int = R.layout.fragment_test

    override fun initListener() {
        super.initListener()
//        btn_system_code
//                .lazyBind { NetConstant.supcon_login(url, edt_username, edt_password) }
//                .invoke { tv_result_content.text = it.toString() }
//        btn_system_code.bind(NetConstant.supcon_login(url, edt_username, edt_password))
//                .invoke(Consumer {
//                    tv_result_content.text = it.toString()
//                })
    }
}