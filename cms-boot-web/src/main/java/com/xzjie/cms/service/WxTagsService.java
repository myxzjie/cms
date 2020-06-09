package com.xzjie.cms.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.model.WxFansTag;
import com.xzjie.cms.model.WxTags;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface WxTagsService extends BaseService<WxTags, Long> {

    Page<WxTags> getTags(Integer page, Integer size, WxTags query);

    List<WxTags> getTagsData();

    void syncTags();

    Set<Long> getByFansId(Long fansId);

    void saveFans(Long fansId, List<Long> tagIds);
}
