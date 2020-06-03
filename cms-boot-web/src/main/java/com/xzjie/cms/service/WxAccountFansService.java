package com.xzjie.cms.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.model.Article;
import com.xzjie.cms.model.WxAccountFans;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WxAccountFansService extends BaseService<WxAccountFans, Long> {

    WxAccountFans getAccountFans(Long id);

    List<WxAccountFans> getAccountFans(WxAccountFans query);

    Page<WxAccountFans> getAccountFans(Integer page, int size, WxAccountFans query);

    void syncAccountFans(String nextOpenId);
}
