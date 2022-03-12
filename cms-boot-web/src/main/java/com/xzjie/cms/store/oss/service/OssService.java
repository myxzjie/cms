package com.xzjie.cms.store.oss.service;

import java.io.InputStream;

/**
 * @author Vito
 * @since 2022/3/11 2:50 下午
 */
public interface OssService {
    String putObject(String filePath, InputStream stream);

    void deleteObject(String filePath);
}
