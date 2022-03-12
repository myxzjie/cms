package com.xzjie.cms.store.local.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "upload.local")
public class LocalProperties {
    private String path;
    private String urlPrefix;
}
