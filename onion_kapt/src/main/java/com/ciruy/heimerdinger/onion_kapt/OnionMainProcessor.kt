package com.ciruy.heimerdinger.onion_kapt

import ciruy.b.heimerdinger.annotation.BuilderClass
import com.ciruy.heimerdinger.onion_kapt.onion.OnionBuilderProcessor
import com.google.auto.service.AutoService
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
class OnionMainProcessor : AbstractProcessor() {
    open var mFiler:Filer?= null
    open var mElement: Elements? =null
    open var mMessager:Messager? = null
    open var mProcessorEnvironment:ProcessingEnvironment? = null

    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        mFiler = processingEnv.filer
        mElement = processingEnv.elementUtils
        mMessager = processingEnv.messager
        mProcessorEnvironment = processingEnv
        OnionBuilderProcessor().process(p1,this)
//        OnionNetProcessor.process(p0, p1)
        return true
    }

    override fun getSupportedAnnotationTypes(): Set<String> {
        return setOf(BuilderClass::class.java.canonicalName)
    }
}

