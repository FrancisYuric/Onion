package com.ciruy.onion_base.rx

import io.reactivex.Flowable
import io.reactivex.Observable

//非惰性操作直接用kt的onEach操作执行，而惰性的就切成RxJava或者RxFlowable进行操作
val <T> T.rxJava
    get() = Observable.just(this)

val <T> T.rxFlow
    get() = Flowable.just(this)

