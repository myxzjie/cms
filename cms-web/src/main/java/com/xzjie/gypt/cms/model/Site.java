package com.xzjie.gypt.cms.model;

import java.util.Date;

public class Site {
    private Long siteId;

    private Long orgId;

    private String siteName;

    private String siteUrl;

    private String keywords;

    private String description;

    private String copyright;

    private String siteStyle;

    private String siteMobileStyle;

    private Date createDate;

    private Date updateDate;

    private Integer state;

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl == null ? null : siteUrl.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright == null ? null : copyright.trim();
    }

    public String getSiteStyle() {
        return siteStyle;
    }

    public void setSiteStyle(String siteStyle) {
        this.siteStyle = siteStyle == null ? null : siteStyle.trim();
    }

    public String getSiteMobileStyle() {
        return siteMobileStyle;
    }

    public void setSiteMobileStyle(String siteMobileStyle) {
        this.siteMobileStyle = siteMobileStyle == null ? null : siteMobileStyle.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}