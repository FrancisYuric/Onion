package com.app.common_upload.annotation.apt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface UploadModuleEntity {
    String name();
    String title() default "";
    String moduleCh() default "";
    String moduleEn() default "";
    String blurSearHint() default "";
    Class[] entities();
    int layout() default 0;
    Class[] uiUpdater() default void.class;
    Class[] viewController();
    Class[] dataFetcher();
    Class[] databaseUpdater();
    Class[] singleUploadController();
}
