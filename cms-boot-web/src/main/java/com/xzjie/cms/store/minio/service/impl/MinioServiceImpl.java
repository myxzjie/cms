package com.xzjie.cms.store.minio.service.impl;

import com.xzjie.cms.store.minio.configure.MinioConfigurationProperties;
import com.xzjie.cms.store.minio.service.MinioService;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author Vito
 * @since 2022/3/11 2:50 下午
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {
    private final MinioClient minioClient;
    private final MinioConfigurationProperties configurationProperties;

    @SneakyThrows
    @Override
    public String putObject(String filePath, InputStream file, String contentType) {
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(configurationProperties.getBucket())
                .object(filePath)
                .stream(file, file.available(), -1)
//                .headers(headers)
                .contentType(contentType)
                .build();
        ObjectWriteResponse response = minioClient.putObject(args);
        log.info("minio put response:{}", response);
        return configurationProperties.getUrlPrefix() + configurationProperties.getBucket() + "/" + filePath;
    }

    @SneakyThrows
    @Override
    public void deleteObject(String filePath) {
        minioClient.removeObject(RemoveObjectArgs
                .builder()
                .bucket(configurationProperties.getBucket())
                .object(filePath)
                .build());
    }
}
