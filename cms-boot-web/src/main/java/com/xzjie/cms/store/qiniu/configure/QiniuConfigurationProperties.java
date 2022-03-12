package com.xzjie.cms.store.qiniu.configure;

import com.xzjie.cms.enums.QiniuRegion;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "upload.qiniu")
public class QiniuConfigurationProperties {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private QiniuRegion region;
    private String urlPrefix;
}
