package com.example.learnkt.ui.baseActivity

import android.os.Bundle
import com.example.learnkt.rx.Permission
import com.example.learnkt.rx.RxPermissions

abstract class BasePermissionActivity:BaseActivity(){
    //permissions needed
    abstract fun permissions():List<Permission>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RxPermissions(this).requestEachCombined()
                .subscribe {  }
    }
}
