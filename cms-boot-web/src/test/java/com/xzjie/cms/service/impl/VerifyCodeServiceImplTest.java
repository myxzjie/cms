package com.xzjie.cms.service.impl;

import com.xzjie.cms.WebApplicationTests;
import com.xzjie.cms.core.utils.JsonUtils;
import com.xzjie.cms.model.VerifyCode;
import com.xzjie.cms.service.VerifyCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VerifyCodeServiceImplTest extends WebApplicationTests {

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Test
    public void getVerifyCodeExpiration() {
        List<VerifyCode> list = verifyCodeService.getVerifyCodeExpiration();
        System.out.printf(">>" + JsonUtils.toJsonString(list));
    }
}
