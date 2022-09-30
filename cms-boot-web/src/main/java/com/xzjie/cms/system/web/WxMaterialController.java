package com.xzjie.cms.system.web;

import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.wechat.dto.WxArticleDto;
import com.xzjie.cms.wechat.dto.WxArticleTemplateDto;
import com.xzjie.cms.wechat.dto.WxArticleTemplateQueryDto;
import com.xzjie.cms.wechat.model.WxArticle;
import com.xzjie.cms.wechat.model.WxArticleTemplate;
import com.xzjie.cms.wechat.service.WxArticleTemplateService;
import io.swagger.annotations.ApiOperation;
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
    public Map<String, Object> createArticle(@RequestBody WxArticleDto articleRequest) {
        articleTemplateService.saveArticle(articleRequest.getId() + "", articleRequest.getList(), articleRequest.getDeleteArticleIds());
        return MapUtils.success();
    }

    @PostMapping("/article/publish")
    public Map<String, Object> publishArticle(@RequestBody WxArticleDto articleRequest) {
        articleTemplateService.updateArticle(articleRequest.getId() + "", articleRequest.getList(), articleRequest.getDeleteArticleIds());
        return MapUtils.success();
    }


    @GetMapping("/article/template")
    public Map<String, Object> articleTemplate(WxArticleTemplateQueryDto query) {
        Page<WxArticleTemplate> articleTemplatePage = articleTemplateService.getArticleTemplate(query);
        return MapUtils.success(articleTemplatePage.getContent(), articleTemplatePage.getTotalElements());
    }

    @GetMapping("/article/template/data")
    public Map<String, Object> getArticleTemplateData(@Validated WxArticleTemplateDto articleTemplateRequest) {
        if (articleTemplateRequest.getPublish() == null) {
            articleTemplateRequest.setPublish(true);
        }
        List<WxArticleTemplate> articleTemplates = articleTemplateService.getArticleTemplate(articleTemplateRequest.toArticleTemplate());

        return MapUtils.success(articleTemplates);
    }

    @PostMapping("/article/template")
    public Map<String, Object> createArticleTemplate(@Validated @RequestBody WxArticleTemplateDto articleTemplateRequest) {
        articleTemplateService.save(articleTemplateRequest.toArticleTemplate());
        return MapUtils.success();
    }

    @ApiOperation("修改微信图文模版")
    @PutMapping("/article/template/{id}")
    public Map<String, Object> updateArticleTemplate(@PathVariable Long id, @Validated @RequestBody WxArticleTemplateDto articleTemplate) {
        articleTemplate.setId(id);
        articleTemplateService.update(articleTemplate.toArticleTemplate());
        return MapUtils.success();
    }

    @ApiOperation("删除微信图文模版")
    @DeleteMapping("/article/template/{id}")
    public Map<String, Object> deleteArticleTemplate(@PathVariable Long id) {
        articleTemplateService.delete(id);
        return MapUtils.success();
    }

    @PostMapping("/article/preview/send")
    public Map<String, Object> sendPreviewArticleTemplate(@Validated @RequestBody WxArticleTemplateDto articleTemplate) {
        articleTemplateService.sendPreviewArticleTemplate(articleTemplate.toArticleTemplate(), articleTemplate.getPreviewFansIds());

        return MapUtils.success();
    }

    @PostMapping("/article/tag/send")
    public Map<String, Object> sendTagArticleTemplate(@Validated @RequestBody WxArticleTemplateDto articleTemplate) {
        articleTemplateService.sendTagArticleTemplate(articleTemplate.toArticleTemplate(), articleTemplate.getTagId());

        return MapUtils.success();
    }

    @PostMapping("/article/fans/send")
    public Map<String, Object> sendFansArticleTemplate(@Validated @RequestBody WxArticleTemplateDto articleTemplate) {
        articleTemplateService.sendFansArticleTemplate(articleTemplate.toArticleTemplate(), articleTemplate.getFansIds());

        return MapUtils.success();
    }
}

