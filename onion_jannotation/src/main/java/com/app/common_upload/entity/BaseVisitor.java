package com.app.common_upload.entity;



public interface BaseVisitor<VIEW> {
    void view(UploadEntity uploadEntity);
    int getLayoutId();
    BaseVisitor view(VIEW view);
    BaseVisitor pos(Integer pos);
}
