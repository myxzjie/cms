package com.xzjie.cms.wechat.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vito
 * @since 2023/6/28 11:02 AM
 */
@Data
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WxXmlMessage {
    private Integer id;

    @XmlElement(name="ToUserName")
    private String toUserName;

    @XmlElement(name="FromUserName")
    private String fromUserName;

    @XmlElement(name="CreateTime")
    private Integer createTime;

    @XmlElement(name="MsgType")
    private String msgType;

    @XmlElement(name="Event")
    private String event;

    @XmlElement(name="EventKey")
    private String eventKey;
}
