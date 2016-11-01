package com.xzjie.gypt.system.model;

import java.util.Date;

public class Org {
    private Long orgId;

    private Long orgPId;

    private String orgName;

    private String unit;

    private Date createDate;

    private Long createUser;

    private Integer state;

    private String orgDesc;
    
    private String orgPName;
    
    private String clientSecret;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getOrgPId() {
        return orgPId;
    }

    public void setOrgPId(Long orgPId) {
        this.orgPId = orgPId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc == null ? null : orgDesc.trim();
    }

	public String getOrgPName() {
		return orgPName;
	}

	public void setOrgPName(String orgPName) {
		this.orgPName = orgPName;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
}