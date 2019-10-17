package com.xzjie.cms.system.web;

import com.xzjie.cms.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hupeng
 * @date 2020-01-09
 */
@Api(tags = "上传统一管理")
@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;
    //    @Value("${file.localUrl}")
    private String localUrl;

//    private final LocalStorageService localStorageService;
//
//    private final QiNiuService qiNiuService;
//
//    public UploadController(LocalStorageService localStorageService, QiNiuService qiNiuService) {
//        this.localStorageService = localStorageService;
//        this.qiNiuService = qiNiuService;
//    }


    @ApiOperation("上传文件")
    @PostMapping
//    @AnonymousAccess
    public ResponseEntity<Object> create(
            @RequestParam(defaultValue = "-1") Long groupId,
            @RequestParam("file") MultipartFile uploadFile) throws IOException {



        String url = uploadService.upload(groupId, uploadFile);

        Map<String, Object> map = new HashMap<>(2);
        map.put("errno", 0);
        map.put("link", url);
        return new ResponseEntity(map, HttpStatus.CREATED);
    }


}
