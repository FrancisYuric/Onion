package com.ciruy.b.heimerdinger.onion_view.memorizer

import androidx.lifecycle.LifecycleOwner
import com.ciruy.onion_base.memory.StrongMemorizers
import com.ciruy.onion_base.memory.WeakMemorizers
import io.reactivex.disposables.CompositeDisposable

object LifeCycleOwnerMemorizer {
    val memorizer:StrongMemorizers<LifecycleOwner?,CompositeDisposable> = StrongMemorizers.applicable {
        CompositeDisposable()
    }
    fun getIfAbsent(lifecycleOwner: LifecycleOwner) = memorizer.computeIfAbsent(lifecycleOwner)
    fun forget(lifecycleOwner: LifecycleOwner) = memorizer.forget(lifecycleOwner)
}