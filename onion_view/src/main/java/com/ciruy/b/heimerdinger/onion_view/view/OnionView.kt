package com.ciruy.b.heimerdinger.onion_view.view

import android.view.View
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun <T : View> flowableClick(view: T): Flowable<Any> = RxView.clicks(view)
        .subscribeOn(AndroidSchedulers.mainThread())
        .observeOn(Schedulers.io())
        .debounce(500, TimeUnit.MILLISECONDS)
        .toFlowable(BackpressureStrategy.DROP)

fun <T : TextView> flowableTextChanges(textView: T): Observable<CharSequence> = RxTextView.textChanges(textView)
        .skipInitialValue()

fun <T> View.bind2Api(flowable: Flowable<T>): Flowable<T> = flowableClick(this)
        .observeOn(Schedulers.io())
        .flatMap { flowable }
        .observeOn(AndroidSchedulers.mainThread())

fun <T> View.lazyBind2Api(flowable: () -> Flowable<T>): Flowable<T> = flowableClick(this)
        .observeOn(Schedulers.io()).flatMap { flowable.invoke() }
        .observeOn(AndroidSchedulers.mainThread())

val <T : TextView> T.content
    get() = this.text.toString()

