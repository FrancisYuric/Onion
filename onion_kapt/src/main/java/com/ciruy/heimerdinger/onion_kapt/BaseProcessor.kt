package com.ciruy.heimerdinger.onion_kapt

import com.squareup.kotlinpoet.FileSpec
import java.io.File
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement
import javax.lang.model.util.ElementFilter
import javax.lang.model.util.Elements

abstract class BaseProcessor {
    fun process(
            roundEnvironment: RoundEnvironment?,
            mAbstractProcessor: OnionMainProcessor
    ): Boolean {
        mProcessingEnvironment = mAbstractProcessor.mProcessorEnvironment
        ElementFilter.typesIn(getElementsAnnotatedWith(roundEnvironment!!, targetAnnotation()))
                .forEach { doOnElement(it) }
        return true
    }

    private lateinit var mProcessingEnvironment: ProcessingEnvironment
    private val mLogger: Logger by lazy { Logger(mProcessingEnvironment.messager) }
    abstract fun <T : TypeElement> doOnElement(typeElement: T)

    /**
     * @see com.squareup.kotlinpoet.FileSpec write file to target directory.
     */
    protected fun FileSpec.writeFile() = writeTo(File(mProcessingEnvironment.options["kapt.kotlin.generated"] as String).apply { mkdirs() })
    protected fun error(logMessage: String) = mLogger.error(logMessage)
    protected fun log(logMessage: String) = mLogger.warning(logMessage)

    /**
     * target annotation to be resolve
     */
    abstract fun targetAnnotation(): Class<out Annotation>

    private fun getElementsAnnotatedWith(
            roundEnvironment: RoundEnvironment,
            targetAnnotation: Class<out Annotation>
    ) = roundEnvironment.getElementsAnnotatedWith(targetAnnotation)
}
