package com.app.com_upload_apt.processor;

import com.app.com_upload_apt.AnnotationProcessor;
import com.app.common_upload.annotation.apt.BuilderClass;
import com.app.common_upload.annotation.apt.OmitMethod;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.ElementFilter;

/**
 * @Author xushiyun
 * @Create-time 10/30/19
 * @Pageage com.app.com_upload_apt.processor
 * @Project hengyiEam2
 * @Email ciruy.victory@gmail.com
 * @Related-classes
 * @Desc 这里的思想和原来设计组件化操作的方式略有不同, 组件化的构建方式其实是把所有的信息合并在一起, 然后集中解决, 所以用到了一个新类专门用于储存
 * 注解中包含的信息,但是当时这么做感觉其实效果并不好,所以呢,这里还是打算在获取到类的时候直接实现类中的信息
 */
public class BuilderProcessor extends BaseProcessor {
    //在原类的类名基础上添加前缀从而生成新类名
    private static final String PREFIX = "Sub";
    private static final String TARGET_CLASS_NAME = "targetClass";
    private Set<String> containedMethods = new HashSet<>();

    @Override
    public void process(RoundEnvironment roundEnv, AnnotationProcessor mAbstractProcessor) {
        //通过创建代码块的方式向类中添加对应的代码内容,遇到复杂的代码信息的时候直接这么实现感觉会好的多
        //依次分析所有被BuilderClass注解注释的类
//        for(TypeElement element:ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(InternalBuilderClass.class)))
//        {
//            Class[] internalBuilderClass = element.getAnnotation(InternalBuilderClass.class).extClass;
//            TypeElement typeElement = TypeElement
//        }
        for (TypeElement elementAnnotatedWithBuilderClass :
                ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(BuilderClass.class))) {
            //直接获取被标记的类名
            String targetAnnotationClass = elementAnnotatedWithBuilderClass.getSimpleName().toString();
            String generatedClassName = PREFIX + targetAnnotationClass;
            //创建类空壳
            ClassName originClassName = ClassName.get(elementAnnotatedWithBuilderClass);
            String packageName = originClassName.packageName();
            ClassName targetClassName = ClassName.get(packageName, generatedClassName);
            TypeSpec.Builder typeSpecBuilder =
                    TypeSpec.classBuilder(generatedClassName)
                            .addMethod(MethodSpec.constructorBuilder()
                                    .addParameter(originClassName, TARGET_CLASS_NAME)
                                    .addModifiers(Modifier.PRIVATE)
                                    .addStatement(CodeBlock.builder().add("this.$L = $L",
                                            TARGET_CLASS_NAME,
                                            TARGET_CLASS_NAME).build())
                                    .build())
                            .addMethod(MethodSpec.methodBuilder("__create")
                                    .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
                                    .addParameter(originClassName, TARGET_CLASS_NAME)
                                    .returns(targetClassName)
                                    .addCode(CodeBlock.builder().addStatement("return new $T($L)",
                                            targetClassName,
                                            TARGET_CLASS_NAME).build())
                                    .build())
                            .addField(FieldSpec.builder(originClassName, TARGET_CLASS_NAME).build());
            //获取被BuilderClass所注释的类中的所有方法
            analyseEnclosedElements(generatedClassName, packageName, typeSpecBuilder, elementAnnotatedWithBuilderClass, mAbstractProcessor);
            TypeElement temp = getSuperClassTypeElement(elementAnnotatedWithBuilderClass);
            while (!temp.getQualifiedName().toString().equals(Object.class.getName())) {
                analyseEnclosedElements(generatedClassName, packageName, typeSpecBuilder, temp, mAbstractProcessor);
                temp = getSuperClassTypeElement(temp);
            }
            //处理完毕之后直接清空当前类的所有缓存信息,不然可能会对后续的同个类的子类的方法就不会重复导入了
            containedMethods.clear();
            typeSpecBuilder
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(MethodSpec.methodBuilder("build").addModifiers(Modifier.PUBLIC)
//                            .addParameter(originClassName, "p1")
                            .returns(originClassName)
                            .addStatement("return $L", TARGET_CLASS_NAME)
                            .build());
            try {
                JavaFile.builder(packageName, typeSpecBuilder.build()).build().writeTo(mAbstractProcessor.mFiler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private TypeElement getSuperClassTypeElement(TypeElement element) {
        DeclaredType declaredType = (DeclaredType) element.getSuperclass();
        return (TypeElement) declaredType.asElement();
    }

    private void analyseEnclosedElements(String generatedClassName, String packageName, TypeSpec.Builder typeSpecBuilder, Element declaredType, AnnotationProcessor mAbstractProcessor) {
        for (ExecutableElement executableElement : ElementFilter.methodsIn(declaredType.getEnclosedElements())) {
            if (executableElement.getAnnotation(OmitMethod.class) != null) continue;
            if (!executableElement.getModifiers().contains(Modifier.PUBLIC)) continue;
            //获取返回类型
            TypeMirror returnType = executableElement.getReturnType();
            //获取参数类型
            List<VariableElement> parameters = (List<VariableElement>) executableElement.getParameters();
            //获取方法名称
            String simpleName = executableElement.getSimpleName().toString();
            StringBuilder characterStr = new StringBuilder().append(returnType.getKind().name()).append(simpleName);
            for (VariableElement variableElement : parameters) {
                characterStr.append(variableElement.asType().getKind().name());
            }
            String character = characterStr.toString();
            if (containedMethods.contains(character)) {
                continue;
            }
            containedMethods.add(character);
            if (simpleName.equals("subBuilder")) continue;
            if (simpleName.equals("toString")) continue;
            //获取宿主类型
            TypeMirror receiverType = executableElement.getReceiverType();
            //获取当前方法抛出类型
            List<TypeMirror> thrownTypes = (List<TypeMirror>) executableElement.getThrownTypes();
            List<? extends TypeParameterElement> typeParameterElements = executableElement.getTypeParameters();
            MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder(simpleName);
            StringBuilder paramString = new StringBuilder();
            for (int i = 0; i < parameters.size(); i++) {
                Modifier[] modifiers = Arrays.copyOf(parameters.get(i).getModifiers().toArray(),
                        parameters.get(i).getModifiers().size(), Modifier[].class);
                methodSpecBuilder.addParameter(ClassName.get(parameters.get(i).asType()), "p" + i, modifiers);
                if (i != parameters.size() - 1)
                    paramString.append("p" + i + ",");
                else {
                    paramString.append("p" + i);
                }
            }
            for (TypeParameterElement typeParameterElement : typeParameterElements) {
                methodSpecBuilder.addTypeVariable(TypeVariableName.get((TypeVariable) typeParameterElement.asType()));
            }
            for (TypeMirror typeMirror : thrownTypes) {
                methodSpecBuilder.addException(TypeName.get(typeMirror));
            }
            methodSpecBuilder.addModifiers(Modifier.PUBLIC)
                    .addStatement("$L.$L($L)", TARGET_CLASS_NAME, simpleName, paramString)
                    .addStatement("return this")
                    .returns(ClassName.get(packageName, generatedClassName));
            typeSpecBuilder.addMethod(methodSpecBuilder.build()).build();
        }
    }
}
