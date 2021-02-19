package com.ciruy.b.heimerdinger.onion_view.callback

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import com.ciruy.b.heimerdinger.onion_view.ext.mCompositeDisposable

object OnionActivityLifecycleCallback : BaseActivityLifecycleCallback() {
    override fun onActivityDestroyed(activity: Activity) {
        if (activity is LifecycleOwner)
            activity.mCompositeDisposable.dispose()
        super.onActivityDestroyed(activity)
    }
}