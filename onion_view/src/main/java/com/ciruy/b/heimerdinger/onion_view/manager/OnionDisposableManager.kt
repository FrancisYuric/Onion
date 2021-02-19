package com.ciruy.b.heimerdinger.onion_view.manager

import android.app.Application
import com.ciruy.b.heimerdinger.onion_view.callback.OnionActivityLifecycleCallback

object OnionDisposableManager {
    fun install(application: Application) {
        application.registerActivityLifecycleCallbacks(OnionActivityLifecycleCallback)
    }
}