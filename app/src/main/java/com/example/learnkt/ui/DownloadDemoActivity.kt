package com.example.learnkt.ui

import android.Manifest
import android.view.View
import com.example.learnkt.R
import com.example.learnkt.bean.Constant
import com.example.learnkt.ui.baseActivity.BasePermissionActivity
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.ac_download_demo.*

class DownloadDemoActivity : BasePermissionActivity(), View.OnClickListener {
    override fun initListeners() {
        main_btn_down1.download(Constant.THUNDER_DOWNLOAD_FULL_URL, Consumer {
            main_progress1.max = it.total.toInt()
            main_progress1.progress = it.progress.toInt()
        })
        main_btn_cancel1.cancelDownload(Constant.THUNDER_DOWNLOAD_FULL_URL)
//        val softMemorizers = SoftMemorizers.applicable<String,String> {
//            LogUtil.e("计算开始 ${System.currentTimeMillis()}")
//            Thread.sleep(1000)
//            LogUtil.e("计算结束 ${System.currentTimeMillis()}")
//            it!!
//        }
//        for (i in 0L..9L) {
//            LogUtil.e("${i}次计算结果 ${softMemorizers.computeIfAbsent(""+(i%3))}")
//        }
    }

    override fun onClick(p0: View?) {
    }

    override fun layout() = R.layout.ac_download_demo
    override fun staticPermission(): List<String> {
        return listOf(Manifest.permission.INTERNET)
    }


}