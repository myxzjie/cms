package com.xzjie.gypt.upload.service;

import com.xzjie.gypt.system.model.Upload;

/**
 * Created by xzjie on 2017/7/25.
 */
public interface Uploader {

    Upload upload(byte[] base64);
}
