package com.example.learnkt.ui.baseActivity

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseDisposableActivity : BaseActivity() {
    var mCompositeDisposable: CompositeDisposable? = null

    fun addDisposable(disposable: Disposable) {
        if (mCompositeDisposable == null)
            mCompositeDisposable = CompositeDisposable()
    }

    override fun onDestroy() {
        mCompositeDisposable?.dispose()
        super.onDestroy()
    }
}