//package com.ciruy.heimerdinger.onion_kapt.onion
//
//import ciruy.b.heimerdinger.annotation.BuilderClass
//import com.ciruy.heimerdinger.onion_kapt.BaseProcessor
//import com.squareup.kotlinpoet.*
//import javax.lang.model.element.Element
//import javax.lang.model.element.Modifier
//import javax.lang.model.element.TypeElement
//import javax.lang.model.type.DeclaredType
//import javax.lang.model.util.ElementFilter
//import kotlin.jvm.Throws
//
//class OnionBuilderProcessor : BaseProcessor() {
//    companion object {
//        const val PREFIX = "Sub"
//        const val TARGET_CLASS_NAME = "targetClass"
//    }
//
//    private val containedMethods: HashSet<String> = hashSetOf()
//    override fun <T : TypeElement> doOnElement(typeElement: T) {
////        typeElement.getAnnotation(BuilderClass::class.java).classArray
//        val originClassName = typeElement.asClassName()
//        val originPackageName = originClassName.packageName()
//
//        val generatedClassSimpleName = "${PREFIX}${typeElement.simpleName}"
//        val generatedClassName = ClassName(originPackageName, generatedClassSimpleName)
//
//        val generatedTypeSpecBuilder = TypeSpec.Companion
//                .classBuilder(generatedClassSimpleName)
//                .primaryConstructor(
//                        FunSpec.constructorBuilder()
//                                .addParameter(TARGET_CLASS_NAME, originClassName)
//                                .addModifiers(KModifier.PRIVATE)
//                                .addStatement("this.$TARGET_CLASS_NAME = $TARGET_CLASS_NAME")
//                                .build())
//                .companionObject(TypeSpec.companionObjectBuilder()
//                        .addFunction(FunSpec.builder("__create")
//                                .addParameter(TARGET_CLASS_NAME, originClassName)
//                                .addStatement("return $generatedClassSimpleName($TARGET_CLASS_NAME)")
//                                .build())
//                        .build())
//                .addFunction(FunSpec.builder("build")
//                        .addModifiers(KModifier.OPEN)
//                        .returns(originClassName)
//                        .addStatement("return $TARGET_CLASS_NAME")
//                        .build())
//                .addProperty(PropertySpec.varBuilder(TARGET_CLASS_NAME, originClassName).build())
//                .addModifiers(KModifier.OPEN)
//        analyseEnclosedElements(generatedClassSimpleName, originPackageName, generatedTypeSpecBuilder, typeElement)
//        var temp = getSuperClassTypeElement(typeElement)
//        while (temp.qualifiedName.toString() != Object::class.java.name) {
//            analyseEnclosedElements(generatedClassSimpleName, originPackageName, generatedTypeSpecBuilder, typeElement)
//            temp = getSuperClassTypeElement(temp)
//        }
//        containedMethods.clear()
//        FileSpec.builder(originPackageName, generatedClassSimpleName)
//                .addType(generatedTypeSpecBuilder.build())
//                .build()
//                .writeFile()
//    }
//
//    private fun getSuperClassTypeElement(element: TypeElement) = (element.superclass as DeclaredType).asElement() as TypeElement
//    private fun analyseEnclosedElements(generatedClassName: String,
//                                        packageName: String,
//                                        typeSpecBuilder: TypeSpec.Builder,
//                                        declaredType: Element) {
//        ElementFilter.methodsIn(declaredType.enclosedElements)
//                .forEach { executableElement ->
//                    //                    if (executableElement.modifiers.contains(Modifier.PUBLIC).not()) return@forEach
//                    val methodReturnType = executableElement.returnType
//                    val methodParameters = executableElement.parameters
//                    val methodSimpleName = executableElement.simpleName.toString()
//                    val characterStrBuilder = StringBuilder().append(methodReturnType.kind.name).append(methodSimpleName)
//                    methodParameters.forEach { characterStrBuilder.append(it.asType().kind.name) }
//                    val character = characterStrBuilder.toString()
//                    if (methodSimpleName == "subBuilder"
//                            || methodSimpleName == "toString"
//                            || methodSimpleName.startsWith("set")
//                            || methodSimpleName.startsWith("get")
//                            || containedMethods.contains(character)) return
//                    else containedMethods.add(character)
//                    val receiverType = executableElement.receiverType
//                    val thrownTypes = executableElement.thrownTypes
//                    val methodSpecBuilder = FunSpec.builder(methodSimpleName)
//                    val paramString = StringBuilder()
//                    methodParameters.forEach { variableElement ->
//                        val modifiers = mutableListOf<Modifier>()
//                        modifiers.addAll(variableElement.modifiers)
//                        methodSpecBuilder.addParameter(ParameterSpec.get(variableElement))
//                    }
//                    val annotationSpec = AnnotationSpec.builder(Throws::class)
//                    if (thrownTypes.isNotEmpty()) {
//                        val throwsValueString = thrownTypes.joinToString { "%T::class" }
//                        annotationSpec.addMember(throwsValueString, *executableElement.thrownTypes.toTypedArray())
//                                .build()
//                    }
//
//                    methodSpecBuilder.addModifiers(KModifier.OPEN)
//                            .addAnnotation(annotationSpec.build())
//                            .addStatement("${TARGET_CLASS_NAME}.${methodSimpleName}(${paramString})")
//                            .addStatement("return this")
//                            .returns(ClassName(packageName, generatedClassName))
//                    typeSpecBuilder.addFunction(methodSpecBuilder.build())
//                }
//    }
//
//    override fun targetAnnotation(): Class<out Annotation> = BuilderClass::class.java
//}