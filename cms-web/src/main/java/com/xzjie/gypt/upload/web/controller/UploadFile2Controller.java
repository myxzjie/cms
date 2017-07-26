package com.xzjie.gypt.upload.web.controller;

import com.alibaba.fastjson.JSON;
import com.xzjie.gypt.common.utils.DateUtils;
import com.xzjie.gypt.common.utils.ImageBase64Utils;
import com.xzjie.gypt.common.utils.RspCode;
import com.xzjie.gypt.common.utils.result.MapResult;
import com.xzjie.gypt.system.model.Upload;
import com.xzjie.gypt.system.service.UploadService;
import com.xzjie.gypt.system.web.WebUtils;
import com.xzjie.oss.OSSUploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;


@SuppressWarnings("restriction")
@Controller
@RequestMapping("upload2")
public class UploadFile2Controller {

    protected Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private UploadService uploadService;


    @RequestMapping(value = "image")
    @ResponseBody
    public synchronized Map<String, Object> uploadImage(String dir, @RequestParam("file") MultipartFile multipartFile,
                                                        HttpServletRequest request) {
        log.info("upload image>>:" + dir);
//		String path2=request.getSession().getServletContext().getRealPath("upload/img/product");
//		System.out.println("path2:"+path2);
        /** 判断文件是否为空,空直接返回上传错误 **/
        if (!multipartFile.isEmpty()) {
            log.info("upload image Filename>>:" + multipartFile.getOriginalFilename());
            try {
                String uploadFileName = multipartFile.getOriginalFilename();
                // 获得文件后缀名
                String suffix = uploadFileName.substring(uploadFileName.lastIndexOf("."));
                if (!WebUtils.getUploadImgageExts().contains(suffix)) {// 检测图片类型
                    return MapResult.mapError("上传失败，图片类型为:" + WebUtils.getUploadImgageExts());
                }
                Long fileSize = multipartFile.getSize();
                if (fileSize > WebUtils.getUploadImgageMaxSize()) {
                    return MapResult
                            .mapError("上传失败：文件大小不能超过[" + WebUtils.getUploadImgageMaxSize() / (1024 * 1024) + "M]");
                }

                if (dir == null) {
                    return MapResult.mapError("上传失败：dir 参数为空");
                }

                dir = dir.endsWith("/") ? (dir + "/") : dir;

                String fileName = System.currentTimeMillis() + "_" + DateUtils.getRandom(6) + suffix;
                String path = dir + fileName;
                String url = OSSUploadUtils.upload(path, multipartFile.getInputStream());

                Upload entity = new Upload();

                entity.setFileName(fileName);
                entity.setUploadFileName(uploadFileName);
                entity.setWebUrl(url);
                entity.setUriPath(path);
                // 类型，0图片，1视频，2音频
                entity.setStype(0);
                entity.setFileSize(Double.valueOf(fileSize));

                uploadService.save(entity);

                log.info("upload image info>>:" + JSON.toJSONString(entity));
                return MapResult.mapOK(entity, "OK");
            } catch (Exception e) {
                log.error("upload image error>>:", e.getMessage());
                e.printStackTrace();
                return MapResult.mapOK(e.getMessage(), "OK");
            }
        }

        return MapResult.mapError("请选择上次的图片");
    }

    @RequestMapping("file")
    @ResponseBody
    public Map<String, Object> uploadFiles(String dir, @RequestParam("file") MultipartFile multipartFile,
                                           HttpServletRequest request) {
        /** 判断文件是否为空,空直接返回上传错误 **/
        if (!multipartFile.isEmpty()) {
            try {
                String uploadFileName = multipartFile.getOriginalFilename();
                // 获得文件后缀名
                String suffix = uploadFileName.substring(uploadFileName.lastIndexOf("."));
                if (!WebUtils.getUploadFileExts().contains(suffix)) {// 检测图片类型
                    return MapResult.mapError(RspCode.R99999, "上传失败，文件类型为:" + WebUtils.getUploadFileExts());
                }
                Long fileSize = multipartFile.getSize();
                if (fileSize > WebUtils.getUploadFileMaxSize()) {
                    return MapResult.mapError(RspCode.R99999,
                            "上传失败：文件大小不能超过[" + WebUtils.getUploadFileMaxSize() / (1024 * 1024) + "M]");
                }

                if (dir == null) {
                    return MapResult.mapError(RspCode.R99999, "上传失败：dir 参数为空");
                }

                dir = dir.endsWith("/") ? (dir + "/") : dir;
                String fileName = System.currentTimeMillis() + "_" + DateUtils.getRandom(6) + suffix;
                String path = dir + fileName;
                String url = OSSUploadUtils.upload(path, multipartFile.getInputStream());

                Upload entity = new Upload();
                entity.setFileName(fileName);
                entity.setUploadFileName(uploadFileName);
                entity.setWebUrl(url);
                entity.setUriPath(path);
                // 类型，0图片，1视频，2音频
                entity.setStype(fileType(suffix));
                entity.setFileSize(Double.valueOf(fileSize));

                uploadService.save(entity);

                return MapResult.mapOK(entity, "OK");
            } catch (Exception e) {
                e.printStackTrace();
                return MapResult.mapOK(e.getMessage(), "OK");
            }
        }

        return MapResult.mapError("请选择上次的图片");
    }


    private Integer fileType(String contentType) {
        // txt,rar,zip,doc,docx,xls,xlsx,jpg,jpeg,gif,png,swf,wmv,avi,wma,mp3,mid
        if ("jpg,jpeg,gif,png".contains(contentType)) {
            return 0;
        } else if ("swf,wmv,avi,wma".contains(contentType)) {
            return 1;
        } else if ("mp3,mid".contains(contentType)) {
            return 2;
        } else if ("txt,doc,docx,xls,xlsx".contains(contentType)) {
            return 3;
        } else if ("rar,zip".contains(contentType)) {
            return 4;
        } else {
            return 99;
        }
    }


}
