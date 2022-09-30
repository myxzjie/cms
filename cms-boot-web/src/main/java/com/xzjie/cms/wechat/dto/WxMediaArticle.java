package com.xzjie.cms.wechat.dto;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 鹰视视科技: www.dev56.com
 *
 * @author: xzjie
 * @create: 2019-01-07 23:36
 **/
public class WxMediaArticle {

    private List<ItemMap> articles;

    private WxMediaArticle() {
        articles = new ArrayList<>();
    }

    public static WxMediaArticle builder() {
        return new WxMediaArticle();
    }

    public static ItemMap getItemMap() {
        return new ItemMap();
    }

    public String build() {
        return JSON.toJSONString(this);
    }

    public WxMediaArticle add(ItemMap itemMap) {
        articles.add(itemMap);
        return this;
    }

    public List<ItemMap> getArticles() {
        return articles;
    }

    public void setArticles(List<ItemMap> articles) {
        this.articles = articles;
    }

    public static class ItemMap extends HashMap<String, Object> {

        public ItemMap() {
        }

        public ItemMap add(String key, Object value) {
            this.put(key, value);
            return this;
        }
    }


}
