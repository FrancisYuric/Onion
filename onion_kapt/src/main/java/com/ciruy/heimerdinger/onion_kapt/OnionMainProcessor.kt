package com.ciruy.heimerdinger.onion_kapt

import ciruy.b.heimerdinger.annotation.BuilderClass
import com.google.auto.service.AutoService
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
class OnionMainProcessor : AbstractProcessor() {
    lateinit var mProcessorEnvironment:ProcessingEnvironment

    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        mProcessorEnvironment = processingEnv
//        OnionBuilderProcessor().process(p1,this)
        return true
    }

    override fun getSupportedAnnotationTypes(): Set<String> {
        return setOf(BuilderClass::class.java.canonicalName)
    }
}

