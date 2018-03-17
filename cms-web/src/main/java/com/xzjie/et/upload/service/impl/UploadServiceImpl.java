package com.xzjie.et.upload.service.impl;

import com.xzjie.common.web.utils.MapResult;
import com.xzjie.core.conf.ConfigManager;
import com.xzjie.common.web.utils.PathFormat;
import com.xzjie.et.upload.service.UploadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

/**
 * Created by asus on 2017/8/16.
 */
@Service("uploadService")
public class UploadServiceImpl implements UploadService{
    private final Logger LOG = LogManager.getLogger(UploadServiceImpl.class);

    @Override
    public Map<String,Object> upload(String directory,MultipartFile file,ConfigManager conf){
        if (file.isEmpty()) {
            return MapResult.mapError("10");
        }

        String fileName=null;
        long fileSize=0;

        try{
            fileName=file.getOriginalFilename();
            fileSize=file.getSize();
            String  contentType= file.getContentType();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            if(!conf.getValue("upload.file.exts").contains(suffix)) {
                return MapResult.mapError("13");
            }
            long maxSize=Long.parseLong(conf.getValue("upload.file.maxSize"));
            if (fileSize >maxSize ) {
                return MapResult.mapError("14","上传的文件大小不能超过[" + maxSize / (1024 * 1024) + "M]");
            }

            String rootPath=conf.getValue("upload.directory");
            String savePath=directory+"/"+PathFormat.parse(conf.getValue("upload.path.format"))+suffix;

            String physicalPath =rootPath +savePath;
            String url=conf.getValue("upload.file.URL")+savePath;
            File targetFile=new File(physicalPath);
            Map<String,Object>  map = valid(targetFile);
            file.transferTo(targetFile);

            map.put( "size", targetFile.length() );
            map.put( "title", targetFile.getName() );
            map.put("url",url);
            map.put("type", suffix);
            map.put("original", fileName);
            return map;

        }catch (Exception e){
            LOG.error("上传文件错误：{}",e.getMessage());
        }
        return MapResult.mapError("15");
    }

    private static Map<String,Object> valid(File file) {
        File parentPath = file.getParentFile();

        if ((!parentPath.exists()) && (!parentPath.mkdirs())) {
            return MapResult.mapError("11");
        }

        if (!parentPath.canWrite()) {
            return MapResult.mapError("12");
        }

        return MapResult.mapOK();
    }

}
