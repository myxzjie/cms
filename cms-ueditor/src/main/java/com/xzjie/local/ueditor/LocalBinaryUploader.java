package com.xzjie.local.ueditor;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.upload.BinaryUploader;
import com.baidu.ueditor.upload.StorageManager;
import com.xzjie.oss.conf.Configuration;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LocalBinaryUploader {

    public static final State save(HttpServletRequest request,
                                   Map<String, Object> conf) {
        FileItemStream fileStream = null;
        boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;

        if (!ServletFileUpload.isMultipartContent(request)) {
            return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
        }

        ServletFileUpload upload = new ServletFileUpload(
                new DiskFileItemFactory());

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

            String physicalPath = (String) conf.get("rootPath") + savePath;

            /* date:2017-10-7 14:34:27
            *  xzjie
            *  修改保存文件路径
            * */
            if(StringUtils.isNotBlank(Configuration.getLocalPath())){
                physicalPath=Configuration.getLocalPath() + savePath;
            }

            InputStream is = fileStream.openStream();
            State storageState = StorageManager.saveFileByInputStream(is,
                    physicalPath, maxSize);
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
}
