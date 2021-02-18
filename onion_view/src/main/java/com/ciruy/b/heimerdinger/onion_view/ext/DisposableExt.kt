package com.ciruy.b.heimerdinger.onion_view.ext

import androidx.lifecycle.LifecycleOwner
import com.ciruy.b.heimerdinger.onion_view.memorizer.LifeCycleOwnerMemorizer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

val LifecycleOwner.mCompositeDisposable: CompositeDisposable
    get() = LifeCycleOwnerMemorizer.getIfAbsent(this)
            ?: error("compositeDisposable can not be null!")

fun LifecycleOwner.addDisposable(disposable: Disposable) {
    mCompositeDisposable.add(disposable)
}


//基于AOP来操作Disposable的回收？我真的是傻，我明明已经基于函数式的存储化技术将所有的
// CompositeDisposable统一化管理了，我竟然还想再去对OnDestroy方法进行间接改造？
