package com.ciruy.b.heimerdinger.onion_view.ext

import android.app.Activity
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.ciruy.b.heimerdinger.onion_view.memorizer.LifeCycleOwnerMemorizer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

val LifecycleOwner.mCompositeDisposable: CompositeDisposable
    get() = LifeCycleOwnerMemorizer.getIfAbsent(this)

fun LifecycleOwner.addDisposable(disposable: Disposable) {
    mCompositeDisposable.add(disposable)
}

//基于AOP来操作Disposable的回收？
val View.ownerActivity: Activity?
    get() = if (this.context is Activity) this.context as Activity else null
