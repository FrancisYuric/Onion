package com.example.learnkt.ui.baseActivity

import com.ciruy.b.heimerdinger.onion_view.activity.BaseActivity

abstract class BaseDisposableActivity(override var layout: Int?) : BaseActivity(layout) {
    constructor() : this(null)
}