package com.app.common_upload.role;


import com.app.common_upload.entity.UploadEntity;
import com.app.common_upload.inter.OnOperateSuccessListener;

public interface BaseUploadDatabaseUpdater {
    
    public abstract void update(UploadEntity uploadEntity);
    
    public abstract void updateAllCheckStatus(boolean checkStatus);
    
    default int clearAllErrMsg() {
        return 0;
    }
    
    default void clearAllErrMsg(OnOperateSuccessListener onOperateSuccessListener) {
        
        int modifiedCount = clearAllErrMsg();
        if (onOperateSuccessListener != null) {
            onOperateSuccessListener.onSuccess("已清除" + modifiedCount + "条错误信息。");
        }
    }
}
