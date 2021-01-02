package com.example.learnkt.api

import android.content.Context
import com.example.learnkt.bean.ResultEntityWrapper
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * 对于Observer的回调，设置对于不同的场景的回调函数
 */
class APIResult<T : ResultEntityWrapper<T>>(context: Context) : Observer<T> {
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable) {

    }


}