package com.xzjie.cms.service.impl;

import com.aliyun.oss.OSS;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.xzjie.cms.configure.AliyunConfigure;
import com.xzjie.cms.configure.LocalProperties;
import com.xzjie.cms.configure.QiniuConfigure;
import com.xzjie.cms.configure.UploadProperties;
import com.xzjie.cms.core.utils.SecurityUtils;
import com.xzjie.cms.minio.service.MinioService;
import com.xzjie.cms.model.Account;
import com.xzjie.cms.model.Pictures;
import com.xzjie.cms.service.PicturesService;
import com.xzjie.cms.service.UploadService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private OSS ossClient;
    @Autowired
    private Auth auth;
    @Autowired
    private UploadProperties properties;
    @Autowired
    private LocalProperties localProperties;
    @Autowired
    private AliyunConfigure aliyunConfig;
    @Autowired
    private QiniuConfigure qiniuConfigure;
    @Autowired
    private UploadManager uploadManager;
    @Autowired
    private PicturesService picturesService;
    @Autowired
    private MinioService minioService;

    @Override
    public String upload(Long groupId, MultipartFile uploadFile) throws IOException {
        //文件新路径
        String fileName = uploadFile.getOriginalFilename();
        String filePath = getFilePath(fileName);
        String url = null;

        switch (properties.getType()) {
            case ALIYUN:
                InputStream inputStream = uploadFile.getInputStream();
                ossClient.putObject(aliyunConfig.getBucketName(), filePath, inputStream);
                //
                url = aliyunConfig.getUrlPrefix() + filePath;
//                pictures.setPath(filePath);
                break;
            case QINIU:
                String uploadToken = auth.uploadToken(qiniuConfigure.getBucket());

                String key = "/" + filePath;
                Response response = uploadManager.put(uploadFile.getBytes(), key, uploadToken);
//                DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
                url = qiniuConfigure.getUrlPrefix() + key;
//                pictures.setPath(key);
                break;
            case MINIO:
                String contentType = uploadFile.getContentType();
                inputStream = uploadFile.getInputStream();
                url = minioService.putObject(filePath, inputStream, contentType);
                break;
            case LOCAL:
            default:
                String path = localProperties.getPath() + filePath;
                File dest = new File(path);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                uploadFile.transferTo(dest.getAbsoluteFile());
                //
                url = localProperties.getUrlPrefix() + filePath;
//                pictures.setPath(dest.getAbsolutePath());
                break;
        }

        Pictures pictures = new Pictures();
        pictures.setPath(filePath);
        pictures.setGroupId(groupId);
        pictures.setUrl(url);
        pictures.setType(1);
        pictures.setName(fileName);
        pictures.setOrigin(properties.getType().name());
        try {
            Long userId = SecurityUtils.getUserId();
            pictures.setUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        picturesService.save(pictures);

        return url;
    }

    private String getFilePath(String sourceFileName) {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        //4.把 2019-01-01  转成  2019/01/01
        String format = localDate.format(dtf);
        return "images/" + format + "/" + System.currentTimeMillis() +
                RandomUtils.nextInt(4) + "." +
                StringUtils.substringAfterLast(sourceFileName, ".");
    }
}
