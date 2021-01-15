package com.app.com_upload_apt.processor.inter;


import com.app.com_upload_apt.AnnotationProcessor;

import javax.annotation.processing.RoundEnvironment;

/**
 * Created by baixiaokang on 16/10/8.
 * 注解处理器接口
 */

public interface IProcessor {
    void process(RoundEnvironment roundEnv, AnnotationProcessor mAbstractProcessor);
}
