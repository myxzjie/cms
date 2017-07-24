package com.xzjie.oss.ueditor;

import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.xzjie.oss.OSSUploadUtils;
import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * Created by xzjie on 2017/7/24.
 */
public class OSSStorageManager {

    public static State saveFileByInputStream(InputStream is, String path,
                                              long maxSize) {
        State state = null;

        try {
            if(is.available() > maxSize) {
                return new BaseState(false, AppInfo.MAX_SIZE);
            }

            OSSUploadUtils.upload(path,is);
            state = new BaseState(true);
            state.putInfo( "size", is.available() );
            state.putInfo( "title", "" );
            return state;
        } catch (IOException e) {
        }

        return new BaseState(false, AppInfo.IO_ERROR);
    }

}
