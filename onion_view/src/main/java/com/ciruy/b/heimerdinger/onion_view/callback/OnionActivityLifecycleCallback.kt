package com.ciruy.b.heimerdinger.onion_view.callback

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import com.ciruy.b.heimerdinger.onion_view.ext.mCompositeDisposable

object OnionActivityLifecycleCallback :BaseActivityLifecycleCallback(){
    override fun onActivityDestroyed(activity: Activity) {
        if(activity is LifecycleOwner) activity.mCompositeDisposable.clear()
        super.onActivityDestroyed(activity)
    }
}