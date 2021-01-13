package com.ciruy.heimerdinger.onion_kapt.onion

import ciruy.b.heimerdinger.annotation.BuilderClass
import com.ciruy.heimerdinger.onion_kapt.BaseProcessor
import com.squareup.kotlinpoet.*
import java.io.File
import javax.lang.model.element.TypeElement

class OnionBuilderProcessor : BaseProcessor() {
    val PREFIX = "Sub"
    val TARGET_CLASS_NAME = "targetClass"
    override fun <T : TypeElement> doOnElement(typeElement: T) {
        typeElement.getAnnotation(BuilderClass::class.java).classArray
        val originClassName = typeElement.asClassName()
        val originPackageName = originClassName.packageName()

        val generatedClassSimpleName = "${PREFIX}${typeElement.simpleName}"
        val generatedClassName = ClassName(originPackageName, generatedClassSimpleName)

        val generatedTypeSpecBuilder = TypeSpec.Companion
                .classBuilder(generatedClassSimpleName)
                .primaryConstructor(
                        FunSpec.constructorBuilder()
                                .addParameter(TARGET_CLASS_NAME, originClassName)
                                .addModifiers(KModifier.PRIVATE)
                                .addStatement("this.$TARGET_CLASS_NAME = $TARGET_CLASS_NAME")
                                .build())
                .companionObject(TypeSpec.companionObjectBuilder()
                        .addFunction(FunSpec.builder("__create")
                                .addParameter(TARGET_CLASS_NAME, originClassName)
                                .addStatement("return $generatedClassSimpleName($TARGET_CLASS_NAME)")
                                .build())
                        .build())
                .addFunction(FunSpec.builder("build")
                        .addModifiers(KModifier.OPEN)
                        .returns(originClassName)
                        .addStatement("return $TARGET_CLASS_NAME")
                        .build())
                .addProperty(PropertySpec.varBuilder(TARGET_CLASS_NAME, originClassName).build())
                .addModifiers(KModifier.OPEN)
        FileSpec.builder(originPackageName, generatedClassSimpleName)
                .addType(generatedTypeSpecBuilder.build())
                .build().writeFile()
    }

    private fun FileSpec.writeFile() {
        val kAptKotlinGeneratedDir = processingEnvironment!!.options["kapt.kotlin.generated"]
        val outputFile = File(kAptKotlinGeneratedDir as String).apply {
            mkdirs()
        }
        writeTo(outputFile.toPath())
    }

    override fun targetAnnotation(): Class<out Annotation> = BuilderClass::class.java
}