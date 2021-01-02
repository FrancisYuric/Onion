package com.example.learnkt.util

import android.app.Dialog
import android.content.Context
import com.example.learnkt.R

object LoadingDialog {

    private var dialog: Dialog? = null

    fun show(context: Context) {
        cancel()
        dialog = Dialog(context)
        dialog?.setContentView(R.layout.dialog_loading)
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.show()
    }

    fun hide() {
        dialog?.hide()
    }

    fun cancel() {
        dialog?.cancel()
    }

    fun dismiss() {

    }

    fun error(errorMes: String?, second2delay: Int) {

    }

    fun success(successMes: String?, second2delay: Int) {

    }

}