package com.xzjie.oss.ueditor;

import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.upload.StorageManager;
import com.xzjie.oss.OSSUploadUtils;
import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * Created by xzjie on 2017/7/24.
 */
public class OSSStorageManager {

    public static State saveBinaryFile(byte[] data, String path) {
        //File file = new File(path);

        //State state = valid(file);

        //if (!state.isSuccess()) {
        //    return state;
        //}
        File file =getTmpFile();

        try {
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(file));
            bos.write(data);
            bos.flush();
            bos.close();

            InputStream is = new FileInputStream(file);
            OSSUploadUtils.upload(path,is);

            file.delete();
        } catch (IOException ioe) {
            return new BaseState(false, AppInfo.IO_ERROR);
        }
        State state = new BaseState(true, path);
        //State state = new BaseState(true, file.getAbsolutePath());
        state.putInfo( "size", data.length );
        state.putInfo( "title", file.getName() );
        return state;
    }

    public static State saveFileByInputStream(InputStream is, String path,
                                              long maxSize) {
        State state = null;

        try {
            if(is.available() > maxSize) {
                return new BaseState(false, AppInfo.MAX_SIZE);
            }
            return saveFileByInputStream(is,path);

//            OSSUploadUtils.upload(path,is);
//            state = new BaseState(true);
//            state.putInfo( "size", is.available() );
//            state.putInfo( "title", "" );
//            return state;
        } catch (IOException e) {
        }

        return new BaseState(false, AppInfo.IO_ERROR);
    }

    public static State saveFileByInputStream(InputStream is, String path) {
        State state = null;

        try {
            OSSUploadUtils.upload(path,is);
            state = new BaseState(true);
            state.putInfo( "size", is.available() );
            state.putInfo( "title", "" );

            return state;
        } catch (IOException e) {
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    private static File getTmpFile() {
        File tmpDir = FileUtils.getTempDirectory();
        String tmpFileName = (Math.random() * 10000 + "").replace(".", "");
        return new File(tmpDir, tmpFileName);
    }

}
