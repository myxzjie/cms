package com.xzjie.cms.store.qiniu.service.impl;

import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.xzjie.cms.store.qiniu.configure.QiniuConfigurationProperties;
import com.xzjie.cms.store.qiniu.service.QiniuService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vito
 * @since 2022/3/11 6:11 下午
 */
@Service
@RequiredArgsConstructor
public class QiniuServiceImpl implements QiniuService {
    private final Auth auth;
    private final UploadManager uploadManager;
    private final QiniuConfigurationProperties properties;

    @SneakyThrows
    @Override
    public String putObject(String filePath, byte[] bytes) {
        String uploadToken = auth.uploadToken(properties.getBucket());

        String key = "/" + filePath;
        Response response = uploadManager.put(bytes, key, uploadToken);
        return properties.getUrlPrefix() + key;
    }

    @Override
    public void deleteObject(String filePath) {

    }
}
