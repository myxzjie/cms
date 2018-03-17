package com.xzjie.client.cms.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xzjie on 2017/8/28.
 */
public class Navs implements Serializable{

    private Long id;

    private String name;

    private String href;

    private int sort;

    private  boolean sub;

    private List<Navs> subNavs;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Navs> getSubNavs() {
        return subNavs;
    }

    public void setSubNavs(List<Navs> subNavs) {
        this.subNavs = subNavs;
    }

    public boolean getSub() {
        return sub;
    }

    public void setSub(boolean sub) {
        this.sub = sub;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
