package com.xzjie.et.core.security;

import com.xzjie.et.system.model.Account;

import java.io.Serializable;

/**
 * 授权用户信息
 */
public final class Principal implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long userId; // 用户ID
    private String username; // 登录名称
    private String nickName; // 昵称
    private String phone; // 手机号
    private String eMail; // 邮箱
    private Long orgId; //组织
    private boolean isMobile; // 是否手机登录
    private String stype;
    private String avatar;
    private Long roleId;
    private String roleCode;
    private Long siteId;

    private Principal(Account model, boolean isMobile) {
        this(model, isMobile, null, null, null);
    }

    public Principal(Account account, boolean isMobile, Long roleId, String roleCode, Long siteId) {
        this.userId = account.getUserId();
        this.username = account.getName();
        this.nickName = account.getNickName();
        this.phone = account.getPhone();
        this.eMail = account.geteMail();
        this.orgId = account.getOrgId();
        this.isMobile = isMobile;
        this.stype = account.getStype();
        this.avatar = account.getAvatar();
        this.roleId = roleId;
        this.roleCode = roleCode;
        this.siteId = siteId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPhone() {
        return phone;
    }

    public String geteMail() {
        return eMail;
    }

    public Long getOrgId() {
        return orgId;
    }

    public boolean isMobile() {
        return isMobile;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }
}