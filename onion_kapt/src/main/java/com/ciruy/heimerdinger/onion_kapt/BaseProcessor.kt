package com.ciruy.heimerdinger.onion_kapt

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement

abstract class BaseProcessor : AbstractProcessor() {
    override fun process(
        p0: MutableSet<out TypeElement>?,
        roundEnvironment: RoundEnvironment?
    ): Boolean {
        getElementsAnnotatedWith(roundEnvironment!!, targetAnnotation())
            .forEach { doOnElement(it) }
        return true
    }

    abstract fun <T : Element> doOnElement(t: T)

    /**
     * target annotation to be resolve
     */
    abstract fun targetAnnotation(): Class<out Annotation>

    private fun getElementsAnnotatedWith(
        roundEnvironment: RoundEnvironment,
        targetAnnotation: Class<out Annotation>
    ) = roundEnvironment.getElementsAnnotatedWith(targetAnnotation)
}