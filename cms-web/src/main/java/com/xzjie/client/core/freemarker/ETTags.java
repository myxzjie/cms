package com.xzjie.client.core.freemarker;


import com.xzjie.client.ad.web.tags.AdTags;
import com.xzjie.client.cms.web.tags.ArticleTags;
import com.xzjie.client.cms.web.tags.NavsTags;
import com.xzjie.client.cms.web.tags.SiteTags;
import com.xzjie.common.web.freemarker.AbstractDefaultTags;

public class ETTags extends AbstractDefaultTags {

    public ETTags() {
        put("site", new SiteTags());
        put("article", new ArticleTags());
        put("navs", new NavsTags());
        put("ad", new AdTags());
    }
}
