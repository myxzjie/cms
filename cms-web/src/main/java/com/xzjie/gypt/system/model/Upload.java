package com.xzjie.gypt.system.model;

import java.util.Date;

public class Upload {
    private Long id;

    private String fileName;

    private String uploadFileName;

    private String webUrl;

    private String uriPath;

    private Date createdete;

    private Date updatedate;

    private Integer stype;

    private Double fileSize;

    private Integer theirType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName == null ? null : uploadFileName.trim();
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? null : webUrl.trim();
    }

    public String getUriPath() {
        return uriPath;
    }

    public void setUriPath(String uriPath) {
        this.uriPath = uriPath == null ? null : uriPath.trim();
    }

    public Date getCreatedete() {
        return createdete;
    }

    public void setCreatedete(Date createdete) {
        this.createdete = createdete;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Integer getStype() {
        return stype;
    }

    public void setStype(Integer stype) {
        this.stype = stype;
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getTheirType() {
        return theirType;
    }

    public void setTheirType(Integer theirType) {
        this.theirType = theirType;
    }
}