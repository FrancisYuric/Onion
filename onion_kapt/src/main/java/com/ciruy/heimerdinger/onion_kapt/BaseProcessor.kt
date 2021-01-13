package com.ciruy.heimerdinger.onion_kapt

import java.util.logging.Logger
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement
import javax.lang.model.util.ElementFilter
import javax.lang.model.util.Elements

abstract class BaseProcessor  {
    fun process(
            roundEnvironment: RoundEnvironment?,
            mAbstractProcessor:OnionMainProcessor
    ): Boolean {
        processingEnvironment = mAbstractProcessor.mProcessorEnvironment
        ElementFilter.typesIn(getElementsAnnotatedWith(roundEnvironment!!, targetAnnotation()))
                .forEach { doOnElement(it) }
        return true
    }

//    protected var onionMainProcessor:OnionMainProcessor? = null
    protected var processingEnvironment:ProcessingEnvironment? = null
    private var elementUtils: Elements? = null
    abstract fun <T : TypeElement> doOnElement(typeElement: T)

    /**
     * target annotation to be resolve
     */
    abstract fun targetAnnotation(): Class<out Annotation>

    private fun getElementsAnnotatedWith(
            roundEnvironment: RoundEnvironment,
            targetAnnotation: Class<out Annotation>
    ) = roundEnvironment.getElementsAnnotatedWith(targetAnnotation)
}
