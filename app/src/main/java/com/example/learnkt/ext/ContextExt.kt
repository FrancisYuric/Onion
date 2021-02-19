package com.example.learnkt.ext

import android.content.Context
import android.text.TextUtils
import com.ciruy.onion_base.util.ToastUtil
import com.ciruy.b.heimerdinger.onion.bean.BaseEntity
import com.example.learnkt.bean.ResultEntityWrapper

fun <T : Context, F : ResultEntityWrapper<M>, M : BaseEntity> T.result(resultEntity: F) {
    if (TextUtils.isEmpty(resultEntity.errorMsg)) ToastUtil.short(this, "登录成功！")
    else ToastUtil.short(this, resultEntity.errorMsg)
}