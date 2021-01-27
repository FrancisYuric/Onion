package com.example.learnkt.ui

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.learnkt.R
import com.jakewharton.rxbinding2.view.RxView


class TestRxViewActivity : Activity() {

    private var mNonUIThread: HandlerThread? = null

    private var mHandler: NonUIThreadHandler? = null

    private val mClickListener = View.OnClickListener { v ->
        when (v?.id) {
            R.id.helloWorld -> {
                val msg = Message.obtain()
                msg.what = MESSAGE_SHOW_DIALOG
                mHandler!!.sendMessage(msg)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNonUIThread = HandlerThread("NonUIThread")
        mNonUIThread!!.start()
        mHandler = NonUIThreadHandler(mNonUIThread!!.looper)
        val showDialogBtn = findViewById<TextView>(R.id.helloWorld)
        showDialogBtn.setOnClickListener(mClickListener)
    }

    private inner class NonUIThreadHandler(looper: Looper) : android.os.Handler(looper) {

        private var mDialog: Dialog? = null
        private val mDialogContent: TextView? = null

        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MESSAGE_SHOW_DIALOG -> {
                    if (mDialog == null) {
                        val dialog =
                                Dialog(this@TestRxViewActivity, R.style.ThemeOverlay_AppCompat_Dialog)
                        dialog.setContentView(R.layout.dialog_layout)
                        val info =
                                dialog.findViewById(R.id.dialog_info) as TextView
                        info.text = "Dialog shows in thread " + Thread.currentThread().id
                        val change = dialog.window?.decorView?.findViewById(R.id.change_btn) as Button
                        change.setOnClickListener {
                            info.text = "onClick is called in thread " + Thread.currentThread().id
                        }
                        RxView.clicks(change)
                                .subscribe {
                                    info.text =
                                            "onClick is called in thread " + Thread.currentThread().id
                                }
                        mDialog = dialog
                    }
                    if (!mDialog!!.isShowing) {

                        mDialog!!.show()
                    }
                }
            }
        }
    }

    companion object {
        private const val MESSAGE_SHOW_DIALOG = 1
    }

}