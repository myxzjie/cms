package com.xzjie.et.wechat.entity;

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
public class MateriaNewsData {

   private List<ItemMap> articles;

   private MateriaNewsData(){
       articles =new ArrayList<>();
   }

    public static MateriaNewsData builder() {
        return new MateriaNewsData();
    }

    public static ItemMap itemMap(){
        return new ItemMap();
    }

    public String build() {
        return JSON.toJSONString(this);
    }

    public MateriaNewsData add(ItemMap itemMap) {
        articles.add(itemMap);
        return this;
    }

    public List<ItemMap> getArticles() {
        return articles;
    }

    public void setArticles(List<ItemMap> articles) {
        this.articles = articles;
    }

    public static class ItemMap extends HashMap<String, String> {

        public ItemMap() {
        }

        public ItemMap add(String key,String value){
            this.put(key,value);
            return this;
        }
    }


}
