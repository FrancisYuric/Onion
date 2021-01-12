package com.ciruy.heimerdinger.onion_kapt

import com.google.auto.service.AutoService
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
class OnionAnnotationProcessor : BaseProcessor(){
    override fun targetAnnotation(): Class<out Annotation> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

abstract class BaseProcessor : AbstractProcessor() {
    override fun process(p0: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        getElementsAnnotatedWith(roundEnvironment!!,targetAnnotation())
//                .filter {  }
        return true
    }
    abstract fun targetAnnotation(): Class<out Annotation>
    fun getElementsAnnotatedWith(roundEnvironment: RoundEnvironment, targetAnnotation: Class<out Annotation>): Set<Element> = roundEnvironment.getElementsAnnotatedWith(targetAnnotation)
}