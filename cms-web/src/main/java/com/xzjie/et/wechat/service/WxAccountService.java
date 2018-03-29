package com.xzjie.et.wechat.service;

import com.xzjie.et.wechat.model.WxAccount;
import com.xzjie.mybatis.core.service.BaseService;

public interface WxAccountService extends BaseService<WxAccount, Long> {

    boolean existByName(String name);

    boolean existByCode(String code);

    WxAccount getWxAccount(WxAccount model);

    WxAccount getWxAccountByCode(String code);

    WxAccount getWxAccountBySiteId(Long siteId);
}
