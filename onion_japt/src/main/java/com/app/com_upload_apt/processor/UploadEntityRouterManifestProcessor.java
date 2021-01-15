package com.app.com_upload_apt.processor;

import com.app.com_upload_apt.AnnotationProcessor;
import com.app.com_upload_apt.helper.TestUploadEntityModel;
import com.app.common_upload.entity.UploadEntity;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.List;

import javax.annotation.processing.RoundEnvironment;

import static com.squareup.javapoet.TypeSpec.classBuilder;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;

public class UploadEntityRouterManifestProcessor extends BaseProcessor {
    public static final String SINGLE_UPLOAD_MODULE = "SingleUploadModule";
    public static final String SINGLE_UPLOAD_MODULE_INSTANCE = "singleUploadModule";
    private String packageName = null;
    private static final ClassName SINGLE_UPLOAD_MODULE_CLASSNAME = ClassName.get("com.supcon.mes.module_single_upload.model.bean", SINGLE_UPLOAD_MODULE);


    @Override
    public void process(RoundEnvironment roundEnv, AnnotationProcessor mAbstractProcessor) {
        final String className = "SingleUploadManifests";

        //创建SingleUploadManifests 类对象
        final TypeSpec.Builder typeSpecBuilder = TypeSpec.enumBuilder(className)
                .addModifiers(PUBLIC)
                .addSuperinterface(ClassName.get("com.app.common_upload.role", "BaseUploadUiUpdater"))
                .addSuperinterface(ClassName.get("com.app.common_upload.role", "BaseUploadDatabaseUpdater"))
                .addSuperinterface(ClassName.get("com.app.common_upload.role", "BaseUploadDataFetcher"));

        final CodeBlock.Builder staticCodeBlockBuilder = CodeBlock.builder();

        final List<TestUploadEntityModel> testUploadEntityModels = analyseAnnotatedElements(roundEnv, mAbstractProcessor);
        for (TestUploadEntityModel testUploadEntityModel : testUploadEntityModels) {
            typeSpecBuilder.addEnumConstant(
                    testUploadEntityModel.moduleEn(),
                    TypeSpec.anonymousClassBuilder(
                            "$T.emptyInstance()" +
                                    ".alias($S).title($S).moduleCh($S).blurSearHint($S)" +
                                    ".baseSingleUploadControllerByDefault()" +
                                    ".baseUploadDatabaseUpdaterByDefault()" +
                                    ".baseUploadDataFetcherByDefault()",
                            SINGLE_UPLOAD_MODULE_CLASSNAME,
                            testUploadEntityModel.alias(),
                            testUploadEntityModel.title(),
                            testUploadEntityModel.moduleCh(),
                            testUploadEntityModel.blurSearHint()).build());
        }

        typeSpecBuilder
                .addField(
                        ClassName.get("com.supcon.mes.module_single_upload.model.bean",
                                SINGLE_UPLOAD_MODULE), SINGLE_UPLOAD_MODULE_INSTANCE, PRIVATE)
                .addMethod(
                        MethodSpec.constructorBuilder()
                                .addParameter(SINGLE_UPLOAD_MODULE_CLASSNAME, SINGLE_UPLOAD_MODULE_INSTANCE)
                                .addStatement("this.$N = $N", SINGLE_UPLOAD_MODULE_INSTANCE, SINGLE_UPLOAD_MODULE_INSTANCE)
                                .build())
                .addMethod(
                        MethodSpec.methodBuilder("getBlurMes")
                                .addModifiers(PUBLIC)
                                .addStatement("return $N.blurMes() == null ? \"\":" +
                                        "$N.blurMes()", SINGLE_UPLOAD_MODULE_INSTANCE, SINGLE_UPLOAD_MODULE_INSTANCE)
                                .returns(String.class).build())
                .addMethod(
                        MethodSpec.methodBuilder("setBlurMes")
                                .addModifiers(PUBLIC)
                                .addParameter(String.class, "blurMes")
                                .addStatement("$N.blurMes(blurMes)").build())
                .addMethod(
                        MethodSpec.methodBuilder("isAllChecked")
                                .addModifiers(PUBLIC)
                                .addStatement("return $N.allChecked()", SINGLE_UPLOAD_MODULE_INSTANCE)
                                .returns(Boolean.class)
                                .build())
                .addMethod(
                        MethodSpec.methodBuilder("setAllChecked")
                                .addModifiers(PUBLIC)
                                .addParameter(Boolean.class, "allChecked")
                                .addStatement("this.$N.allChecked(allChecked)", SINGLE_UPLOAD_MODULE_INSTANCE).build())
                .addMethod(
                        MethodSpec.methodBuilder("changCheckStatus")
                                .addModifiers(PUBLIC).addStatement("$N.changeCheckStatus()", SINGLE_UPLOAD_MODULE_INSTANCE).build())
                .addMethod(
                        MethodSpec.methodBuilder("getUploadTitle")
                                .addModifiers(PUBLIC).addStatement("return $N.title()", SINGLE_UPLOAD_MODULE_INSTANCE)
                                .returns(String.class).build())
                .addMethod(
                        MethodSpec.methodBuilder("getModule")
                                .addModifiers(PUBLIC)
                                .addStatement("return $N.module()", SINGLE_UPLOAD_MODULE_INSTANCE)
                                .returns(String.class).build())
                .addMethod(
                        MethodSpec.methodBuilder("getLayoutId")
                                .addModifiers(PUBLIC)
                                .addStatement("return $N.layoutId()", SINGLE_UPLOAD_MODULE_INSTANCE).returns(int.class).build())
                .addMethod(
                        MethodSpec.methodBuilder("getBlurSearHint")
                                .addModifiers(PUBLIC).addStatement("return $N.blurSearHint()", SINGLE_UPLOAD_MODULE_INSTANCE).returns(String.class).build())

                .addMethod(
                        MethodSpec.methodBuilder("update")
                                .addModifiers(PUBLIC)
                                .addParameter(UploadEntity.class, "uploadEntity")
                                .addAnnotation(Override.class)
                                .addStatement("$N.baseUploadDatabaseUpdater().update(uploadEntity)", SINGLE_UPLOAD_MODULE_INSTANCE).build()
                )
                .addMethod(
                        MethodSpec.methodBuilder("updateAllCheckStatus")
                                .addModifiers(PUBLIC)
                                .addParameter(boolean.class, "checkStatus")
                                .addAnnotation(Override.class)
                                .addStatement("$N.baseUploadDatabaseUpdater().updateAllCheckStatus(checkStatus)", SINGLE_UPLOAD_MODULE_INSTANCE).build()
                )
                .addMethod(
                        MethodSpec.methodBuilder("clearAllErrMsg")
                                .addModifiers(PUBLIC)
                                .addAnnotation(Override.class)
                                .addStatement("$N.baseUploadDatabaseUpdater().clearAllErrMsg()", SINGLE_UPLOAD_MODULE_INSTANCE).build()
                )
        ;


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
                .addSuperinterface(ClassName.get("com.supcon.common.com_router.api", "IRouter"))
                .addJavadoc("@API entities router created by apt\n" +
                        "支持组件化多模块\n" +
                        "add by xushiyun\n");
    }
}
