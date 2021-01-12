package com.ciruy.heimerdinger.onion_kapt.onion

import ciruy.b.heimerdinger.annotation.OnionNet
import com.ciruy.heimerdinger.onion_kapt.BaseProcessor
import javax.lang.model.element.Element

class OnionNetProcessor : BaseProcessor() {
    override fun <T : Element> doOnElement(element: T) {
    }

    override fun targetAnnotation() = OnionNet::class.java
}