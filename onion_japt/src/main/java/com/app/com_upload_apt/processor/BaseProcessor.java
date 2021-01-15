package com.app.com_upload_apt.processor;

import com.app.com_upload_apt.AnnotationProcessor;
import com.app.com_upload_apt.helper.TestUploadEntityModel;
import com.app.com_upload_apt.processor.inter.IProcessor;
import com.app.common_upload.annotation.apt.UploadModuleEntity;
import com.squareup.javapoet.ClassName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;

public abstract class BaseProcessor implements IProcessor {
    protected String packageName;
    
    protected String getClassName(AnnotationProcessor mAbstractProcessor, TypeElement elementAnnotatedWithUploadEntity, String name) {
        String className = null;
        for (AnnotationMirror am : elementAnnotatedWithUploadEntity.getAnnotationMirrors()) {
            if (UploadModuleEntity.class.getName().equals(am.getAnnotationType().toString())) {
                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : am.getElementValues().entrySet()) {
                    if (name.equals(entry.getKey().getSimpleName().toString())) {
                        AnnotationValue annotationValue = entry.getValue();
//                        mAbstractProcessor.mMessenger
//                                .printMessage(Diagnostic.Kind.NOTE, "entities: " + annotationValue.toString());
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
    
    protected List<TestUploadEntityModel> analyseAnnotatedElements(RoundEnvironment roundEnv, AnnotationProcessor mAbstractProcessor) {
        final List<ClassName> classNames = new ArrayList<>();
        final List<TestUploadEntityModel> testUploadEntityModels = new ArrayList<>();
        for (TypeElement elementAnnotatedWithUploadEntity : ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(UploadModuleEntity.class))) {
            
            final ClassName currentType = ClassName.get(elementAnnotatedWithUploadEntity);
            
            if (classNames.contains(currentType)) {
                continue;
            } else {
                classNames.add(currentType);
            }
            final TestUploadEntityModel testUploadEntityModel = new TestUploadEntityModel();
            
            
            testUploadEntityModel
                    .className(getClassName(mAbstractProcessor, elementAnnotatedWithUploadEntity, "entities"))
                    .viewController(getClassName(mAbstractProcessor, elementAnnotatedWithUploadEntity, "viewController"))
                    .uiUpdater(getClassName(mAbstractProcessor, elementAnnotatedWithUploadEntity, "uiUpdater"))
                    .dataFetcher(getClassName(mAbstractProcessor, elementAnnotatedWithUploadEntity, "dataFetcher"))
                    .databaseUpdater(getClassName(mAbstractProcessor, elementAnnotatedWithUploadEntity, "databaseUpdater"))
                    .singleUploadController(getClassName(mAbstractProcessor, elementAnnotatedWithUploadEntity, "singleUploadController"))
                    .layoutId(elementAnnotatedWithUploadEntity.getAnnotation(UploadModuleEntity.class).layout())
                    .title(elementAnnotatedWithUploadEntity.getAnnotation(UploadModuleEntity.class).title())
                    .moduleCh(elementAnnotatedWithUploadEntity.getAnnotation(UploadModuleEntity.class).moduleCh())
                    .moduleEn(elementAnnotatedWithUploadEntity.getAnnotation(UploadModuleEntity.class).moduleEn())
                    .blurSearHint(elementAnnotatedWithUploadEntity.getAnnotation(UploadModuleEntity.class).blurSearHint())
                    .alias(elementAnnotatedWithUploadEntity.getAnnotation(UploadModuleEntity.class).name());
            testUploadEntityModels.add(testUploadEntityModel);
            if (packageName == null) {
                String temp = elementAnnotatedWithUploadEntity.getEnclosingElement().toString();
                if (temp.contains(".ui")) {
                    packageName = temp.substring(0, temp.lastIndexOf(".ui"));
                } else {
                    packageName = temp;
                }
            }
        }
        return testUploadEntityModels;
    }
}
