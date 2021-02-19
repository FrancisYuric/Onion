package com.ciruy.b.heimerdinger.onion_view.ext

import androidx.lifecycle.LifecycleOwner
import com.ciruy.b.heimerdinger.onion_view.memorizer.LifeCycleOwnerMemorizer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

public val LifecycleOwner.mCompositeDisposable: CompositeDisposable
    get() = LifeCycleOwnerMemorizer.getIfAbsent(this)
            ?: error("compositeDisposable can not be null!")

fun LifecycleOwner.addDisposable(disposable: Disposable) {
    mCompositeDisposable.add(disposable)
}

//基于AOP来操作Disposable的回收？