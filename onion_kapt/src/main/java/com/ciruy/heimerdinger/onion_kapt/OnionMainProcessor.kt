package com.ciruy.heimerdinger.onion_kapt

import ciruy.b.heimerdinger.annotation.OnionNet
import com.google.auto.service.AutoService
import javax.annotation.processing.Processor
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
class OnionMainProcessor : BaseProcessor() {
    override fun <T : Element> doOnElement(t: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun targetAnnotation() = OnionNet::class.java
}

