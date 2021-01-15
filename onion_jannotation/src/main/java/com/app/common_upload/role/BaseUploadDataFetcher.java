package com.app.common_upload.role;

import com.app.common_upload.entity.UploadEntity;

import java.util.List;

public interface BaseUploadDataFetcher<T extends UploadEntity> {

    /**
     * 获取所有符合上传条件的巡检上传信息
     * @return 获取结果
     */
    List<T> genUploadEntityList();

    List<T> genUploadEntityList(String blurMes, Boolean allChecked);

    /**
     * 巡检分页模糊搜索内容搜索,每页10个
     *
     * @param blurMes 模糊搜索内容
     * @param pageNum 页码
     * @return 根据模糊搜索信息和页码获取的搜索结果
     */
    List<T> getRefreshList(String blurMes, int pageNum);

    long getUploadItemCount();
}
