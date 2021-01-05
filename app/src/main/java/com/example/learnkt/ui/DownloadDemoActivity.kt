package com.example.learnkt.ui

import com.example.learnkt.R
import com.example.learnkt.rx.Permission
import com.example.learnkt.ui.baseActivity.BasePermissionActivity

class DownloadDemoActivity :BasePermissionActivity(){

    override fun layout() = R.layout.ac_download_demo
    override fun staticPermission(): List<Permission> {
        return emptyList()
    }


}