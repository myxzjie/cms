package com.xzjie.cms.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class WxMessage implements Serializable {
    private Long id;

    private Long pId;

    private Long siteId;

    private Long userId;

    private String msgtype;

    private String title;

    private String description;

    private String thumbMediaId;

    private String mediaId;

    private String content;

    private Date createDate;

    private Date updateDate;

    private String thumbMedia;

    private String media;

    private Integer showCoverPic;

    private Integer needOpenComment;

    private Integer onlyFansCanComment;

    private Integer status;

    private String digest;

    private String author;

    private String contentSourceUrl;

    private List<WxMessage> messages;

}
