package com.xzjie.cms.store.minio.configure;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
