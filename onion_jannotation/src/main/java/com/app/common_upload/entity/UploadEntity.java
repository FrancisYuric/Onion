package com.app.common_upload.entity;

public interface UploadEntity {
    boolean checkUploadStatus();
    boolean isChecked();
    String getSingleUploadId();
    String getAlias();
    String getUploadErrMes();
    void clearUploadErrMes();
    void setAdapterPos(Integer pos);
    Integer adapterPos();
    UploadEntity checked(boolean isChecked);
    void register(BaseVisitor baseVisitor);
}
