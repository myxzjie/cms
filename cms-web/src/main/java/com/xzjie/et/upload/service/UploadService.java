package com.xzjie.et.upload.service;

import com.xzjie.core.conf.ConfigManager;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by asus on 2017/8/16.
 */
public interface UploadService {

    Map<String,Object> upload(String dir,MultipartFile file,ConfigManager conf);
}
