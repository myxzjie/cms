package com.xzjie.cms.store.minio.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vito
 * @since 2022/3/11 2:16 下午
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "upload.minio")
public class MinioConfigurationProperties {
    private String url;
    private String accessKey;
    private String secretKey;
    private String secure;
    private String bucket;
    private String metricName;
    private String urlPrefix;
}
