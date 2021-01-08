package com.ciruy.b.heimerdinger.onion_view

import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun flowableClick(view: View): Flowable<Any> {
    return RxView.clicks(view)
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .debounce(2, TimeUnit.SECONDS)
            .toFlowable(BackpressureStrategy.DROP)
}

fun <T> View.bind2Api(flowable: Flowable<T>): Flowable<T> {
    return flowableClick(this)
            .observeOn(Schedulers.io())
            .flatMap { flowable }
}


