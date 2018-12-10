package com.xzjie.et.wechat.model;

import java.io.Serializable;

public class WxGroupFollow implements Serializable {
    private Long id;

    private Long groupId;

    private Long followId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getFollowId() {
        return followId;
    }

    public void setFollowId(Long followId) {
        this.followId = followId;
    }
}