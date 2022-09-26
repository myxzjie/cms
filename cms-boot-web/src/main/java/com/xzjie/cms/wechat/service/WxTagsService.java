package com.xzjie.cms.wechat.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.wechat.model.WxTags;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface WxTagsService extends BaseService<WxTags> {

    Page<WxTags> getTags(Integer page, Integer size, WxTags query);

    List<WxTags> getTagsData();

    void syncTags();

    Set<Long> getByFansId(Long fansId);

    void saveFans(Long fansId, List<Long> tagIds);
}
