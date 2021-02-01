package com.example.learnkt.ui

import android.Manifest
import com.example.learnkt.R
import com.example.learnkt.bean.Constant
import com.example.learnkt.ui.baseActivity.BasePermissionActivity
import kotlinx.android.synthetic.main.ac_download_demo.*

class DownloadDemoActivity : BasePermissionActivity(R.layout.ac_download_demo) {
    override fun initListeners() {
        main_btn_down1.download(Constant.THUNDER_DOWNLOAD_FULL_URL) {
            main_progress1.max = it.total.toInt()
            main_progress1.progress = it.progress.toInt()
        }
        main_btn_cancel1.cancelDownload(Constant.THUNDER_DOWNLOAD_FULL_URL)
    }

    override fun staticPermission() = listOf(Manifest.permission.INTERNET)
}