package com.xzjie.cms.system.web;


import com.xzjie.cms.core.utils.MapUtils;
import com.xzjie.cms.dto.WxFansTagRequest;
import com.xzjie.cms.dto.WxTagsRequest;
import com.xzjie.cms.model.WxTags;
import com.xzjie.cms.service.WxTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/wx-tags")
public class WxTagsController {

    @Autowired
    private WxTagsService tagsService;

    @GetMapping("/data")
    public Map<String, Object> getTagsData() {
        List<WxTags> tags = tagsService.getTagsData();
        return MapUtils.success(tags);
    }

    @GetMapping("/list")
    public Map<String, Object> getTags(@Validated WxTagsRequest tagsRequest) {
        Page<WxTags> tagsPage = tagsService.getTags(tagsRequest.getPage(), tagsRequest.getSize(), tagsRequest.toTags());

        return MapUtils.success(tagsPage.getContent(), tagsPage.getTotalElements());
    }

    @GetMapping("/fans/{fansId}")
    public Map<String, Object> getFansTag(@PathVariable Long fansId) {
        Set<Long> tags = tagsService.getByFansId(fansId);
        return MapUtils.success(tags);
    }

    @PostMapping("/fans/save")
    public Map<String, Object> saveFans(@Validated @RequestBody WxFansTagRequest fansTagRequest) {
        tagsService.saveFans(fansTagRequest.getFansId(),fansTagRequest.getTagIds());
        return MapUtils.success();
    }

    @PostMapping("/sync")
    public Map<String, Object> syncTags() {
        tagsService.syncTags();
        return MapUtils.success();
    }

    @PostMapping("/create")
    public Map<String, Object> create(@Validated @RequestBody WxTagsRequest tagsRequest) {
        tagsService.save(tagsRequest.toTags());
        return MapUtils.success();
    }

    @PutMapping("/update/{id}")
    public Map<String, Object> update(@PathVariable Long id, @Validated @RequestBody WxTagsRequest tagsRequest) {
        tagsRequest.setId(id);
        tagsService.update(tagsRequest.toTags());
        return MapUtils.success();
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        tagsService.delete(id);
        return MapUtils.success();
    }


}
