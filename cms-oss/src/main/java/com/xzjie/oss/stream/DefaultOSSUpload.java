package com.xzjie.oss.stream;

import java.io.InputStream;
import java.util.List;

import com.aliyun.oss.OSSClient;
import com.xzjie.oss.OSSClientFactory;

public abstract class DefaultOSSUpload {

    protected OSSUpload ossUpload;

    protected OSSClient client = OSSClientFactory.create();

    private String bucketName = "b-cdn";

    private String key;

    public abstract String upload(InputStream stream);

    public abstract String getDomain();

    public abstract List<String> list(String prefix);

    public void close() {
        client.shutdown();
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
