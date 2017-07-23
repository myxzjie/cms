package com.xzjie.oss.ueditor;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.upload.StorageManager;
import com.xzjie.oss.OSSUploadUtils;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 同步上传文件到阿里云OSS
 * Created by xzjie on 2017/7/24.
 */
public class OSSUploader {

    public static final State save(HttpServletRequest request,Map<String, Object> conf) {
        FileItemStream fileStream = null;
        boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;

        if (!ServletFileUpload.isMultipartContent(request)) {
            return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
        }

        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

        if ( isAjaxUpload ) {
            upload.setHeaderEncoding( "UTF-8" );
        }

        try {
            FileItemIterator iterator = upload.getItemIterator(request);

            while (iterator.hasNext()) {
                fileStream = iterator.next();

                if (!fileStream.isFormField())
                    break;
                fileStream = null;
            }

            if (fileStream == null) {
                return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
            }

            String savePath = (String) conf.get("savePath");
            String originFileName = fileStream.getName();
            String suffix = FileType.getSuffixByFilename(originFileName);

            originFileName = originFileName.substring(0,
                    originFileName.length() - suffix.length());
            savePath = savePath + suffix;

            long maxSize = ((Long) conf.get("maxSize")).longValue();

            if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }

            savePath = PathFormat.parse(savePath, originFileName);

           // String physicalPath = (String) conf.get("rootPath") + savePath;

            //physicalPath = WebUtils.getUploadImageDirectory()+ savePath;

            InputStream is = fileStream.openStream();
            //State storageState = StorageManager.saveFileByInputStream(is,physicalPath, maxSize);
            State storageState = OSSStorageManager.saveFileByInputStream(is,savePath, maxSize);
            is.close();

            if (storageState.isSuccess()) {
                storageState.putInfo("url", PathFormat.format(savePath));
                storageState.putInfo("type", suffix);
                storageState.putInfo("original", originFileName + suffix);
            }

            return storageState;
        } catch (FileUploadException e) {
            return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
        } catch (IOException e) {
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);

        return list.contains(type);
    }
    public boolean upload(JSONObject stateJson,
                          HttpServletRequest request) {


        String key = stateJson.getString("url").replaceFirst("/", "");
//        OSSUploadUtils.upload(key)
//        try {
//            FileInputStream fileInputStream = new FileInputStream(new File(
//                    SystemUtil.getProjectRootPath() + key));
//            PutObjectResult result = ObjectService.putObject(client,
//                    OSSClientProperties.bucketName, key, fileInputStream);
//            logger.debug("upload file to aliyun OSS object server success. ETag: "
//                    + result.getETag());
//            return true;
//        } catch (FileNotFoundException e) {
//            logger.error("upload file to aliyun OSS object server occur FileNotFoundException.");
//        } catch (NumberFormatException e) {
//            logger.error("upload file to aliyun OSS object server occur NumberFormatException.");
//        } catch (IOException e) {
//            logger.error("upload file to aliyun OSS object server occur IOException.");
//        }
        return false;
    }
}
