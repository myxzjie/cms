package com.xzjie.cms.demo;

import com.qiniu.util.Md5;
import com.xzjie.cms.WebApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class DemoServiceTest {


    @Test
    void receivePsrJobRun() {
        System.out.println(Md5.md5(("medical" + Md5.md5("admin".getBytes())).getBytes()));
    }
}
