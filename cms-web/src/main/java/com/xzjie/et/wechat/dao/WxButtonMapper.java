package com.xzjie.et.wechat.dao;

import com.xzjie.et.wechat.model.WxAccount;
import com.xzjie.et.wechat.model.WxButton;
import com.xzjie.mybatis.core.dao.BaseMapper;

import java.util.List;

public interface WxButtonMapper extends BaseMapper<WxButton, Long> {

    List<WxButton> selectTree(WxButton t);
}
