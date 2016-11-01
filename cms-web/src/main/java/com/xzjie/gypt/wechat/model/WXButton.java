package com.xzjie.gypt.wechat.model;

import java.util.Date;
import java.util.List;

public class WXButton {
	private Long buttonId;

    private Long userId;

    private Long pButtonId;

    private String name;

    private String buttonKey;

    private String type;

    private String url;

    private String mediaId;

    private Integer orders;

    private Date createDate;
    
    private List<WXButton> children;
    
    private String perentName;

    public Long getButtonId() {
        return buttonId;
    }

    public void setButtonId(Long buttonId) {
        this.buttonId = buttonId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getpButtonId() {
        return pButtonId;
    }

    public void setpButtonId(Long pButtonId) {
        this.pButtonId = pButtonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getButtonKey() {
        return buttonKey;
    }

    public void setButtonKey(String buttonKey) {
        this.buttonKey = buttonKey == null ? null : buttonKey.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public List<WXButton> getChildren() {
		return children;
	}

	public void setChildren(List<WXButton> children) {
		this.children = children;
	}

	public String getPerentName() {
		return perentName;
	}

	public void setPerentName(String perentName) {
		this.perentName = perentName;
	}
}