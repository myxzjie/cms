package com.xzjie.cms.dto;


import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class WxMessagePreview {

    private String touser;

    private String towxname;

    private String msgtype;

    private ItemMap text;

    private ItemMap mpnews;

//    private String send_ignore_reprint;

    private WxMessagePreview() {
        text = new ItemMap();
        mpnews =new ItemMap();
    }

    public static WxMessagePreview builder() {
        return new WxMessagePreview();
    }

    public String build() {
        return JSON.toJSONString(this);
    }

    public WxMessagePreview add(String content) {
        text.put("content", content);
        return this;
    }

    public WxMessagePreview addMediaId(String mediaId){
        mpnews.put("media_id",mediaId);
        return this;
    }

//    public String getTouser() {
//        return touser;
//    }
//
//    public void setTouser(String touser) {
//        this.touser = touser;
//    }
//
//    public String getMsgtype() {
//        return msgtype;
//    }
//
//    public void setMsgtype(String msgtype) {
//        this.msgtype = msgtype;
//    }
//
//    public ItemMap getText() {
//        return text;
//    }
//
//    public ItemMap getMpnews() {
//        return mpnews;
//    }
//
//    public void setMpnews(ItemMap mpnews) {
//        this.mpnews = mpnews;
//    }
//
//    public String getSend_ignore_reprint() {
//        return send_ignore_reprint;
//    }
//
//    public void setSend_ignore_reprint(String send_ignore_reprint) {
//        this.send_ignore_reprint = send_ignore_reprint;
//    }

    public class ItemMap extends HashMap<String, String> {

        public ItemMap() {
        }

        public ItemMap(String key, String value) {
            this.put(key, value);
        }
    }
}
