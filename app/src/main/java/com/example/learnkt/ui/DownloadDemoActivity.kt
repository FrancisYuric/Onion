package com.example.learnkt.ui

import android.Manifest
import android.view.View
import com.example.learnkt.R
import com.example.learnkt.bean.Constant
import com.example.learnkt.bean.DownloadInfo
import com.example.learnkt.manager.DownloadManager
import com.example.learnkt.rx.DownloadObserver
import com.example.learnkt.ui.baseActivity.BasePermissionActivity
import com.example.learnkt.util.LogUtil
import com.example.learnkt.util.ToastUtil
import kotlinx.android.synthetic.main.ac_download_demo.*

class DownloadDemoActivity : BasePermissionActivity(), View.OnClickListener {
    override fun initListeners() {
    }

    override fun onClick(p0: View?) {
        when (p0) {

            main_btn_down1 -> {
                DownloadManager.instance().download(Constant.DOWNLOAD_URL_BASE, object : DownloadObserver(null
                        , null) {
                    override fun onComplete() {
                        if (downloadInfo != null) {
                            ToastUtil.short(this@DownloadDemoActivity,
                                    downloadInfo?.mFileName + "-DownloadComplete")
                        }
                    }

                    override fun onNext(t: DownloadInfo) {
                        super.onNext(t)
                        LogUtil.e("${t.total} - ${t.progress} ")
                        main_progress1.max = t.total.toInt()
                        main_progress1.progress = t.progress.toInt()
                    }
                })
            }
//                    main_btn_down2
//                    main_btn_down3
            main_btn_cancel1 -> {
                DownloadManager.instance().cancel(Constant.DOWNLOAD_URL_BASE)
            }
//                    main_btn_cancel2
//                    main_btn_cancel3
        }
    }

    override fun layout() = R.layout.ac_download_demo
    override fun staticPermission(): List<String> {
        return listOf(Manifest.permission.INTERNET)
    }


}