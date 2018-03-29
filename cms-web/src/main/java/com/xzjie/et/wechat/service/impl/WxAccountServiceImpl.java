package com.xzjie.et.wechat.service.impl;

import com.xzjie.et.wechat.dao.WxAccountMapper;
import com.xzjie.et.wechat.model.WxAccount;
import com.xzjie.et.wechat.service.WxAccountService;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.core.service.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("wxAccountService")
public class WxAccountServiceImpl extends AbstractBaseService<WxAccount, Long> implements WxAccountService {

    @Autowired
    private WxAccountMapper wxAccountMapper;

    @Override
    protected BaseMapper<WxAccount, Long> getMapper() {
        return wxAccountMapper;
    }


    @Override
    public boolean existByName(String name) {
        WxAccount model = new WxAccount();
        model.setName(name);
        return wxAccountMapper.exist(model) > 0;
    }

    @Override
    public boolean existByCode(String code) {
        WxAccount model = new WxAccount();
        model.setCode(code);
        return wxAccountMapper.exist(model) > 0;
    }

    @Override
    public WxAccount getWxAccount(WxAccount model) {

        return wxAccountMapper.selectWxAccount(model);
    }

    @Override
    public WxAccount getWxAccountByCode(String code) {
        WxAccount model = new WxAccount();
        model.setCode(code);
        return this.getWxAccount(model);
    }

    @Override
    public WxAccount getWxAccountBySiteId(Long siteId) {
        return this.getWxAccount(new WxAccount(siteId));
    }
}
