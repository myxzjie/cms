package com.xzjie.cms.configure;

import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.xzjie.cms.enums.QiniuRegion;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "upload.qiniu")
public class QiniuConfigure {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private QiniuRegion region;
    private String urlPrefix;

    @Bean
    public Auth getAuth() {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth;
    }

    @Bean
    public UploadManager uploadManager(com.qiniu.storage.Configuration config) {
        UploadManager uploadManager = new UploadManager(config);
        return uploadManager;
    }

    /**
     * 华东机房,配置自己空间所在的区域
     */
    @Bean
    public com.qiniu.storage.Configuration qiniuCfg() {
        return new com.qiniu.storage.Configuration(getRegion());
    }

    /**
     * 构建七牛空间管理实例
     *
     * @param auth
     * @param config
     * @return
     */
    @Bean
    public BucketManager bucketManager(Auth auth, com.qiniu.storage.Configuration config) {
        return new BucketManager(auth, config);
    }

    private Region getRegion() {

        switch (region) {
            case HUADONG:
                return Region.huadong();
            case HUABEI:
                return Region.huabei();
            case HUANAN:
                return Region.huanan();
            case BEIMEI:
                return Region.beimei();
            case XINJIAPO:
                return Region.xinjiapo();
            default:
                return Region.autoRegion();
        }
    }
}
