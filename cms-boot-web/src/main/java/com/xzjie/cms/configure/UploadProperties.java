package com.xzjie.cms.configure;

import com.xzjie.cms.enums.UploadType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "upload")
public class UploadProperties {

    private UploadType type;
}
