package com.xzjie.gypt.cms.model;

import java.util.Date;

public class Comment {
    private Long commentId;

    private Long contentId;

    private Long commentPId;

    private String content;

    private Integer status;

    private Long author;

    private Date createDate;

    private String ip;

    private Integer approveStatus;

    private Long approveAuthor;

    private Date approveDate;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Long getCommentPId() {
        return commentPId;
    }

    public void setCommentPId(Long commentPId) {
        this.commentPId = commentPId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Long getApproveAuthor() {
        return approveAuthor;
    }

    public void setApproveAuthor(Long approveAuthor) {
        this.approveAuthor = approveAuthor;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }
}