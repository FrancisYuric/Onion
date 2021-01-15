package com.app.com_upload_apt.processor;

import com.app.com_upload_apt.AnnotationProcessor;
import com.app.com_upload_apt.helper.TestUploadEntityModel;
import com.app.common_upload.annotation.apt.UploadModuleEntity;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import static com.squareup.javapoet.TypeSpec.classBuilder;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

public class UploadEntityRouterProcessor extends BaseProcessor {

    @Override
    public void process(RoundEnvironment roundEnv, AnnotationProcessor mAbstractProcessor) {
        final String className = "EntityRouter";

        //创建IntentRouter类空壳
        TypeSpec.Builder typeSpecBuilder = createIntentRouterTypeSpecBuilder(className);

        //通过创建代码块的方式向类中添加对应的代码内容，遇到复杂的代码信息的时候直接这么实现感觉会好的多
        CodeBlock.Builder staticCodeBlockBuilder = CodeBlock.builder();

        //获取EntityRouterManager的className
        ClassName routerManagerClassName = ClassName.get("com.supcon.mes.middleware", "EntityRouterManager");
        ClassName testUploadEntityClassName = ClassName.get("com.supcon.mes.middleware.model.bean", "TestUploadEntity");

        MethodSpec.Builder getMapMethodSpecBuilder = MethodSpec.methodBuilder("getMap")
                .addJavadoc("@created by apt \n")
                .addModifiers(PRIVATE)
                .addStatement("return map")
                .returns(Map.class);

        //创建EntityRouter的公开获取layoutId和对应Entity类型的方法
        MethodSpec.Builder getLayoutIdByAliasMethodSpecBuilder =
                genMethodSpecBuilder("getLayoutId","layoutId",testUploadEntityClassName,routerManagerClassName,Integer.class);
        MethodSpec.Builder getUploadEntityType =
                genMethodSpecBuilder("getUploadEntityClass","className",testUploadEntityClassName,routerManagerClassName,String.class);
        MethodSpec.Builder getViewControllerType =
                genMethodSpecBuilder("getViewControllerClass","viewController",testUploadEntityClassName, routerManagerClassName,String.class);
        final List<TestUploadEntityModel> testUploadEntityModels = analyseAnnotatedElements(roundEnv, mAbstractProcessor);
        if (testUploadEntityModels.size() <= 0) {
            return;
        }

        for (TestUploadEntityModel testUploadEntityModel : testUploadEntityModels) {
            staticCodeBlockBuilder
                    .addStatement("$T.singleton().register($S,$T.newInstance()\n.layoutId($L)\n.className($S)\n" +
                                    ".viewController($S)\n.uiUpdater($S)\n.dataFetcher($S)\n.databaseUpdater($S)\n.singleUploadController($S))"
                            , routerManagerClassName, testUploadEntityModel.alias(), testUploadEntityClassName
                            , testUploadEntityModel.layoutId(), testUploadEntityModel.className()
                            , testUploadEntityModel.viewController(), testUploadEntityModel.uiUpdater()
                            , testUploadEntityModel.dataFetcher(), testUploadEntityModel.databaseUpdater(), testUploadEntityModel.singleUploadController());
            staticCodeBlockBuilder
                    .addStatement("singleton().map.put($S,$T.newInstance()\n.layoutId($L)\n.className($S)\n" +
                                    ".viewController($S)\n.uiUpdater($S)\n.dataFetcher($S)\n.databaseUpdater($S)\n.singleUploadController($S))"
                            , testUploadEntityModel.alias(), testUploadEntityClassName
                            , testUploadEntityModel.layoutId(), testUploadEntityModel.className()
                            , testUploadEntityModel.viewController(), testUploadEntityModel.uiUpdater()
                            , testUploadEntityModel.dataFetcher(), testUploadEntityModel.databaseUpdater(), testUploadEntityModel.singleUploadController());
        }
        MethodSpec.Builder constructor = MethodSpec.constructorBuilder().addModifiers(PRIVATE);
        FieldSpec.Builder singletonFieldSpecBuilder = FieldSpec
                .builder(ClassName.get(packageName, "EntityRouter"), "SINGLETON", PRIVATE, STATIC, FINAL)
                .initializer("new EntityRouter()");
        FieldSpec.Builder mapFieldSpecBuild = FieldSpec.builder(Map.class, "map")
                .addModifiers(PRIVATE)
                .initializer("new $T<String,$T>()", ClassName.get("java.util", "HashMap"),
                        ClassName.get("com.supcon.mes.middleware.model.bean", "TestUploadEntity"));
        MethodSpec.Builder singletonMethodSpecBuilder = MethodSpec.methodBuilder("singleton").returns(ClassName.get(packageName, "EntityRouter"))
                .addModifiers(PUBLIC, STATIC).addCode(CodeBlock.builder()
                        .addStatement("return SINGLETON").build());

        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();

        TypeSpec clazz = typeSpecBuilder
                .addMethod(getMapMethodSpecBuilder.build())
                .addMethod(getLayoutIdByAliasMethodSpecBuilder.build())
                .addMethod(getUploadEntityType.build())
                .addMethod(getViewControllerType.build())
                .addMethod(constructor.build())
                .addStaticBlock(staticCodeBlockBuilder.build())
                .addField(singletonFieldSpecBuilder.build())
                .addField(mapFieldSpecBuild.build())
                .addMethod(singletonMethodSpecBuilder.build())
                .build();

        try {
            JavaFile.builder(packageName, clazz).build().writeTo(mAbstractProcessor.mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private MethodSpec.Builder genMethodSpecBuilder(String methodName,
                                                    String fieldName,
                                                    ClassName testUploadEntityClassName,
                                                    ClassName routerManagerClassName,
                                                    Class<?> returnType) {
        return MethodSpec.methodBuilder(methodName)
                .addJavadoc("@created by apt \n")
                .addModifiers(PUBLIC, STATIC)
                .addParameter(String.class, "alias")
                .beginControlFlow("if(singleton().getMap().containsKey(alias)) ")
                .addStatement("return (($T)singleton().getMap().get(alias))."+fieldName+"()",
                        testUploadEntityClassName)
                .endControlFlow()
                .addStatement("return $T.singleton().getTestUploadEntityByAlias(alias)."+fieldName+"()",
                        routerManagerClassName)
                .returns(returnType);
    }

    @Override
    protected String getClassName(AnnotationProcessor mAbstractProcessor,
                                  TypeElement elementAnnotatedWithUploadEntity, String name) {
        String className = null;
        for (AnnotationMirror am : elementAnnotatedWithUploadEntity.getAnnotationMirrors()) {
            if (UploadModuleEntity.class.getName().equals(am.getAnnotationType().toString())) {
                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry :
                        am.getElementValues().entrySet()) {
                    if (name.equals(
                            entry.getKey().getSimpleName().toString())) {
                        AnnotationValue annotationValue = entry.getValue();
                        className = annotationValue.toString()
                                .replace(".class", "")
                                .replace("{", "")
                                .replace("}", "");
                        break;
                    }
                }
            }
        }
        return className;
    }


    /**
     * 根据类名称创建对应的类
     *
     * @param className 类名称
     * @return 生成对应类
     */
    private TypeSpec.Builder createIntentRouterTypeSpecBuilder(String className) {
        return classBuilder(className)
                .addModifiers(PUBLIC, FINAL)
                .addSuperinterface(ClassName.get("com.supcon.common.com_router.api",
                        "IRouter"))
                .addJavadoc("@API entities router created by apt\n" +
                        "支持组件化多模块\n" +
                        "add by xushiyun\n");
    }
}
