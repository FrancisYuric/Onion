package com.app.com_upload_apt.helper;

public class TestUploadEntityModel {
    private String classNames;
    private Integer layoutId;
    private String title;
    private String moduleCh;
    private String moduleEn;
    private String blurSearHint;
    private String alias;
    private String viewController;
    private String uiUpdater;
    private String dataFetcher;
    private String databaseUpdater;
    private String singleUploadController;

    public String viewController() {
        return viewController;
    }

    public String title() {
        return title;
    }

    public TestUploadEntityModel title(String title) {
        this.title = title;
        return this;
    }

    public String moduleCh() {
        return moduleCh;
    }

    public TestUploadEntityModel moduleCh(String module) {
        this.moduleCh = module;
        return this;
    }

    public String moduleEn() {
        return moduleEn;
    }

    public TestUploadEntityModel moduleEn(String module) {
        this.moduleEn = module;
        return this;
    }

    public String blurSearHint() {
        return blurSearHint;
    }

    public TestUploadEntityModel blurSearHint(String blurSearHint) {
        this.blurSearHint = blurSearHint;
        return this;
    }

    public TestUploadEntityModel viewController(String viewController) {
        this.viewController = viewController;
        return this;
    }

    private TestUploadEntityModel(String className, Integer layoutId) {
        this.classNames = className;
        this.layoutId = layoutId;
    }

    public TestUploadEntityModel() {
    }

    public String className() {
        return classNames;
    }

    public TestUploadEntityModel className(String className) {
        this.classNames = className;
        return this;
    }

    public Integer layoutId() {
        return layoutId;
    }

    public TestUploadEntityModel layoutId(Integer layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public String alias() {
        return alias;
    }

    public TestUploadEntityModel alias(String alias) {
        this.alias = alias;
        return this;
    }

    public String uiUpdater() {
        return uiUpdater;
    }

    public TestUploadEntityModel uiUpdater(String uiUpdater) {
        this.uiUpdater = uiUpdater;
        return this;
    }

    public String dataFetcher() {
        return dataFetcher;
    }

    public TestUploadEntityModel dataFetcher(String dataFetcher) {
        this.dataFetcher = dataFetcher;
        return this;
    }

    public String databaseUpdater() {
        return databaseUpdater;
    }

    public TestUploadEntityModel databaseUpdater(String databaseUpdater) {
        this.databaseUpdater = databaseUpdater;
        return this;
    }

    public String singleUploadController() {
        return singleUploadController;
    }

    public TestUploadEntityModel singleUploadController(String singleUploadController) {
        this.singleUploadController = singleUploadController;
        return this;
    }
}
