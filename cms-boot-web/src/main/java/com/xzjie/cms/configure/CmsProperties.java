package com.xzjie.cms.configure;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = CmsProperties.PREFIX)
public class CmsProperties {
    public static final String PREFIX = "cms.host";

    private String url;
    private String systemUrl;
    private Integer webUrl;
}
