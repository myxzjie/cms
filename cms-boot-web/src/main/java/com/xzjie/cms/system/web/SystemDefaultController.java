package com.xzjie.cms.system.web;

import com.xzjie.cms.core.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

@RestController
public class SystemDefaultController  {

    @GetMapping("/system/home")
    public String home() {
        return "hi";
    }

}
