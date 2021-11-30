package com.xzjie.cms.service;


import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.model.WxArticle;
import com.xzjie.cms.model.WxArticleTemplate;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WxArticleTemplateService extends BaseService<WxArticleTemplate> {

    WxArticleTemplate getArticleTemplate(Long id);

    List<WxArticleTemplate> getArticleTemplate(WxArticleTemplate query);

    Page<WxArticleTemplate> getArticleTemplate(Integer page, int size, WxArticleTemplate query);

    List<WxArticle> getArticle(String newId);

    void batchDeleteArticle(List<String> list);

    void saveArticle(String newsId, List<WxArticle> list, List<String> articleIds);

    void updateArticle(String newsId, List<WxArticle> list, List<String> articleIds);

    void sendPreviewArticleTemplate(WxArticleTemplate articleTemplate, List<Long> fansIds);

    void sendTagArticleTemplate(WxArticleTemplate articleTemplate, Long tagId);

    void sendFansArticleTemplate(WxArticleTemplate articleTemplate, List<Long> fansIds);
}
