package com.xzjie.cms.wechat.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.wechat.model.WxAccountFans;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WxAccountFansService extends BaseService<WxAccountFans> {

    WxAccountFans getAccountFans(Long id);

    List<WxAccountFans> getAccountFans(WxAccountFans query);

    Page<WxAccountFans> getAccountFans(Integer page, int size, WxAccountFans query);

    Page<WxAccountFans> getAccountFans(Integer page, int size, WxAccountFans query, Long tagId);

    void syncAccountFans(String nextOpenId);
}
