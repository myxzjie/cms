package com.xzjie.cms.configure;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.security.PrivateKey;
import java.security.PublicKey;

@Data
@ConfigurationProperties(prefix = SecurityProperties.PREFIX)
public class SecurityProperties {
    public static final String PREFIX = "auth.jwt";

    private SignatureAlgorithm algorithm;
    private String secret;
    private Integer expired;
    private String keyAlias;
    private String keyStore;
    private String keyPassword;
    private String keyStorePassword;
}
