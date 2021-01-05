package com.example.learnkt.ui.baseActivity

import android.os.Bundle
import android.widget.Toast
import com.example.learnkt.rx.Permission
import com.example.learnkt.rx.RxPermissions

abstract class BasePermissionActivity : BaseDisposableActivity() {
    //staticPermission needed
    abstract fun staticPermission(): List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(staticPermission().isNotEmpty())
            addDisposable(
                RxPermissions(this).requestEachCombined(Permission.create(staticPermission()))
                .subscribe { permission ->
                    //允许权限
                    if (permission.granted) {

                    }
                    //不再提醒
                    else if (permission.shouldShowRequestPermissionRationale) {
                        onRejectShowShowRequestPermissionRationale()
                    }
                    //不允许权限
                    else {
                        onRejectPermission()
                    }
                })
    }

    //拒绝权限
    private fun onRejectPermission() {

    }

    //拒绝且不再提醒,默认退出当前activity
    fun onRejectShowShowRequestPermissionRationale(){
        onBackPressed()
    }
}
