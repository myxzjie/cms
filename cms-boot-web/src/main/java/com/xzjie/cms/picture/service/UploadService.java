package com.xzjie.cms.picture.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    String upload(Long groupId,MultipartFile uploadFile) throws IOException;
}
