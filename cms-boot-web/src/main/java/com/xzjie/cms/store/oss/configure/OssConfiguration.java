package com.xzjie.cms.store.oss.configure;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vito
 * @since 2022/3/11 5:56 下午
 */
@Configuration
public class OssConfiguration {
    @Autowired
    private OssConfigurationProperties properties;

    @Bean
    public OSS ossClient() {
        ClientBuilderConfiguration configuration = new ClientBuilderConfiguration();
        configuration.setSupportCname(properties.isSupportCname());
        OSS ossClient = new OSSClientBuilder().build(properties.getEndpoint(), properties.getAccessKeyId(), properties.getAccessKeySecret(), configuration);
        return ossClient;
    }

}
