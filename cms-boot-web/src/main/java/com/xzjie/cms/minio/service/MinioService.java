package com.xzjie.cms.minio.service;

import java.io.InputStream;

/**
 * @author Vito
 * @since 2022/3/11 2:50 下午
 */
public interface MinioService {
    String putObject(String filePath, InputStream file, String contentType);

    void deleteObject(String filePath);
}
