package com.xzjie.cms.configure;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "upload.aliyun")
public class AliyunConfigure {
    private boolean supportCname;
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String urlPrefix;

    @Bean
    public OSS getOSSClient() {
        ClientBuilderConfiguration configuration = new ClientBuilderConfiguration();
        configuration.setSupportCname(supportCname);
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, configuration);
        return ossClient;
    }

}
