package com.xzjie.cms.minio.configure;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Vito
 * @since 2022/3/11 2:19 下午
 */
@Configuration
//@ComponentScan
//@ConditionalOnClass(MinioClient.class)
//@EnableConfigurationProperties(MinioConfigurationProperties.class)
public class MinioConfiguration {
    @Autowired
    private MinioConfigurationProperties minioConfigurationProperties;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient;
        minioClient = MinioClient.builder()
                .endpoint(minioConfigurationProperties.getUrl())
                .credentials(minioConfigurationProperties.getAccessKey(), minioConfigurationProperties.getSecretKey())
                .build();
//        BucketExistsArgs existsArgs = BucketExistsArgs.builder()
//                .bucket(minioConfigurationProperties.getBucket())
//                .build();
//
//        boolean hasExist = minioClient.bucketExists(existsArgs);
//        if (!hasExist) {
//            MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder()
//                    .bucket(minioConfigurationProperties.getBucket())
//                    .build();
//            minioClient.makeBucket(makeBucketArgs);
//        }
        return minioClient;

    }
}
