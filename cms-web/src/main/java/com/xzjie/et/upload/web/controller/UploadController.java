package com.xzjie.et.upload.web.controller;

import com.xzjie.core.conf.ConfigManager;
import com.xzjie.et.upload.service.UploadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by xzjie on 2017/7/9.
 */
@Controller
public class UploadController {
    private final Logger LOG = LogManager.getLogger(UploadController.class);

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "upload/{dir}")
    @ResponseBody
    public Map<String,Object> upload(@PathVariable String dir, @RequestParam("file") MultipartFile multipartFile,
                      HttpServletRequest request) {

        ConfigManager conf= ConfigManager.getConf();
        return uploadService.upload(dir,multipartFile,conf);
    }



}
