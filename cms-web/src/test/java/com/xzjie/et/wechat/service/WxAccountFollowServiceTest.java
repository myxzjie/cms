package com.xzjie.et.wechat.service;

import com.xzjie.et.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WxAccountFollowServiceTest extends BaseTest {

    @Autowired
    private WxAccountFollowService wxAccountFollowService;

    @Test
    public void test(){
        wxAccountFollowService.batchSyncAccountFollow(1L,"");
    }
}
