package com.example.learnkt.ui.baseActivity

import android.util.Pair
import android.view.View
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open abstract class BaseDisposableActivity : BaseActivity() {

    var mCompositeDisposable: CompositeDisposable? = null

    fun addDisposable(disposable: Disposable) {
        if (mCompositeDisposable == null)
            mCompositeDisposable = CompositeDisposable()
    }


    fun  View.bind(vConnect2Disposable:((Pair<View, Disposable>)->Unit)): (()->Disposable)->Unit    {
        return {
            vConnect2Disposable.invoke(Pair(this,it.invoke()))
        }
    }

    fun <T>((()->Disposable)).bind(disposable2Flowable:(Pair<Disposable,Flowable<T>>)->Unit):(()->Flowable<T>)->Unit{
        return {
            disposable2Flowable.invoke(Pair(this.invoke(),it.invoke()))
        }
    }

    override fun onDestroy() {
        mCompositeDisposable?.dispose()
        super.onDestroy()
    }
}