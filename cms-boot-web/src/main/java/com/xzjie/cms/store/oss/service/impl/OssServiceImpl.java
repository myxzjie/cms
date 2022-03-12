package com.xzjie.cms.store.oss.service.impl;

import com.aliyun.oss.OSS;
import com.xzjie.cms.store.oss.configure.OssConfigurationProperties;
import com.xzjie.cms.store.oss.service.OssService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author Vito
 * @since 2022/3/11 5:59 下午
 */
@Service
@RequiredArgsConstructor
public class OssServiceImpl implements OssService {
    private final OSS ossClient;
    private final OssConfigurationProperties properties;

    @Override
    public String putObject(String filePath, InputStream stream) {
        ossClient.putObject(properties.getBucketName(), filePath, stream);
        return properties.getUrlPrefix() + filePath;
    }

    @Override
    public void deleteObject(String filePath) {
        ossClient.deleteObject(properties.getBucketName(), filePath);
    }
}
