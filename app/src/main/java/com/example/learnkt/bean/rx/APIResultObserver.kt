package com.example.learnkt.bean.rx

import com.example.learnkt.bean.ResultEntityWrapper
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class APIResultObserver<T> : Observer<ResultEntityWrapper<T>> {

    abstract class NoObserver<T> : APIResultObserver<T>() {
        override fun onSubscribe(d: Disposable) {
            super.onSubscribe(d)
            start()
        }
    }

    abstract class NoObservable<T> : APIResultObserver<T>() {
        override fun onNext(t: ResultEntityWrapper<T>) {
            super.onNext(t)
            start()
        }
    }

    abstract fun start()
    abstract fun success(target: T?)
    abstract fun fail(errMes: Throwable)

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: ResultEntityWrapper<T>) {

    }

    override fun onError(e: Throwable) {

    }
}