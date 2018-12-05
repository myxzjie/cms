package com.xzjie.et.wechat.service.impl;

import com.xzjie.core.utils.StringUtils;
import com.xzjie.et.wechat.entity.WxAccessToken;
import com.xzjie.et.wechat.model.WxAccount;
import com.xzjie.et.wechat.service.WxAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.et.wechat.dao.WxAccountFollowMapper;
import com.xzjie.et.wechat.model.WxAccountFollow;
import com.xzjie.et.wechat.service.WxAccountFollowService;
import com.xzjie.mybatis.core.dao.BaseMapper;
import com.xzjie.mybatis.core.service.AbstractBaseService;

import java.util.List;
import java.util.Map;

@Service("wxAccountFollowService")
public class WxAccountFollowServiceImpl extends AbstractBaseService<WxAccountFollow, Long> implements WxAccountFollowService {
    private Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    private WxAccountFollowMapper wxAccountFollowMapper;
    @Autowired
    private WechatHelper wechatHelper;

    @Autowired
    private WxAccountService wxAccountService;

    @Override
    protected BaseMapper<WxAccountFollow, Long> getMapper() {
        return wxAccountFollowMapper;
    }

    @Override
    public void batchSyncAccountFollow(Long siteId, String nextOpenId) {
        try {
            WxAccount wxAccount = wxAccountService.getWxAccountBySiteId(siteId);
            WxAccessToken accessToken = wechatHelper.getAccessToken(wxAccount);

            Map<String, Object> map = wechatHelper.getAccountFollowList(accessToken.getAccess_token(), "");
            if (map == null || map.size() < 1) {
                LOG.debug(">>return 返回");
                return;
            }
            String next_openid = map.get("next_openid").toString();
            List<WxAccountFollow> accountFollows = (List<WxAccountFollow>) map.get("accountFollows");
            if (accountFollows != null && accountFollows.size() > 0) {
                // 批量插入
                for (WxAccountFollow accountFollow : accountFollows) {
                    accountFollow.setSiteId(siteId);
                    if (wxAccountFollowMapper.exist(accountFollow) > 0) {
                        wxAccountFollowMapper.updateByOpenId(accountFollow);
                    } else {
                        this.save(accountFollow);
                    }
                }
            }
            if (StringUtils.isNotBlank(next_openid)) {
                this.batchSyncAccountFollow(siteId, next_openid);
            }
        } catch (Exception e) {
            LOG.error("批量同步公众号用户错误.{}", e);
        }


    }
}
