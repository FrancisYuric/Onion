package com.ciruy.heimerdinger.onion_kapt.onion

import ciruy.b.heimerdinger.annotation.OnionNet
import com.ciruy.heimerdinger.onion_kapt.BaseProcessor
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement

class OnionNetProcessor : BaseProcessor() {
    override fun <T : TypeElement> doOnElement(typeElement: T) {
    }

    override fun targetAnnotation() = OnionNet::class.java

}