package com.xzjie.cms.system.web;

import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.dto.WxArticleRequest;
import com.xzjie.cms.dto.WxArticleTemplateRequest;
import com.xzjie.cms.model.WxArticle;
import com.xzjie.cms.model.WxArticleTemplate;
import com.xzjie.cms.service.WxArticleTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wx-material")
public class WxMaterialController {
    @Autowired
    private WxArticleTemplateService articleTemplateService;

    @GetMapping("/article/{newsId}")
    public Map<String, Object> article(@PathVariable String newsId) {
        List<WxArticle> list = articleTemplateService.getArticle(newsId);
        return MapUtils.success(list);
    }

    @PostMapping("/article/create")
    public Map<String, Object> createArticle(@RequestBody WxArticleRequest articleRequest) {
        articleTemplateService.saveArticle(articleRequest.getId() + "", articleRequest.getList(), articleRequest.getDeleteArticleIds());
        return MapUtils.success();
    }

    @PostMapping("/article/publish")
    public Map<String, Object> publishArticle(@RequestBody WxArticleRequest articleRequest) {
        articleTemplateService.updateArticle(articleRequest.getId() + "", articleRequest.getList(), articleRequest.getDeleteArticleIds());
        return MapUtils.success();
    }

    @GetMapping("/article/template")
    public Map<String, Object> articleTemplate(@Validated WxArticleTemplateRequest articleTemplateRequest) {
        Page<WxArticleTemplate> articleTemplatePage = articleTemplateService.getArticleTemplate(articleTemplateRequest.getPage(), articleTemplateRequest.getSize(), articleTemplateRequest.toArticleTemplate());

        return MapUtils.success(articleTemplatePage.getContent(), articleTemplatePage.getTotalElements());
    }


    @PostMapping("/article/template")
    public Map<String, Object> createArticleTemplate(@Validated @RequestBody WxArticleTemplateRequest articleTemplateRequest) {
        articleTemplateService.save(articleTemplateRequest.toArticleTemplate());

        return MapUtils.success();
    }

    @PostMapping("/article/template/send")
    public Map<String, Object> sendArticleTemplatePreview(@Validated @RequestBody WxArticleTemplateRequest articleTemplate) {
        articleTemplateService.sendArticleTemplatePreview(articleTemplate.toArticleTemplate(), articleTemplate.getFansIds());

        return MapUtils.success();
    }
}

