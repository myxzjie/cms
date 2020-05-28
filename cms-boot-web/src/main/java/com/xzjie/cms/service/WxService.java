package com.xzjie.cms.service;


import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.model.WxArticle;
import com.xzjie.cms.model.WxArticleTemplate;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface WxService {

    WxArticleTemplate getArticleTemplate(Long id);

    Page<WxArticleTemplate> getArticleTemplate(Integer page, int size, WxArticleTemplate query);

    List<WxArticle> getArticle(String newId);

    void batchDeleteArticle(List<String> list);

    void saveArticle(String newsId, List<WxArticle> list, List<String> articleIds);

    void updateArticle(String newsId, List<WxArticle> list, List<String> articleIds) throws IOException, WxErrorException;
}
