package com.xzjie.et.ad.model;

import java.io.Serializable;
import java.util.Date;

public class AdPosition implements Serializable {
    private Long id;

    private Long userId;

    private String positionName;

    private Short adWidth;

    private Short adHeight;

    private String positionModel;

    private String positionDesc;

    private Boolean enabled;

    private String theme;

    private Date createDate;

    private String positionStyle;

    private Long siteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    public Short getAdWidth() {
        return adWidth;
    }

    public void setAdWidth(Short adWidth) {
        this.adWidth = adWidth;
    }

    public Short getAdHeight() {
        return adHeight;
    }

    public void setAdHeight(Short adHeight) {
        this.adHeight = adHeight;
    }

    public String getPositionModel() {
        return positionModel;
    }

    public void setPositionModel(String positionModel) {
        this.positionModel = positionModel == null ? null : positionModel.trim();
    }

    public String getPositionDesc() {
        return positionDesc;
    }

    public void setPositionDesc(String positionDesc) {
        this.positionDesc = positionDesc == null ? null : positionDesc.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPositionStyle() {
        return positionStyle;
    }

    public void setPositionStyle(String positionStyle) {
        this.positionStyle = positionStyle == null ? null : positionStyle.trim();
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
}