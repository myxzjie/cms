package com.xzjie.cms.store.qiniu.service;

import java.io.InputStream;

/**
 * @author Vito
 * @since 2022/3/11 6:10 下午
 */
public interface QiniuService {
    String putObject(String filePath, byte[] bytes);

    void deleteObject(String filePath);
}
