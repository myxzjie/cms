package com.xzjie.oss.ueditor;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.MultiState;
import com.baidu.ueditor.define.State;
import com.xzjie.oss.OSSUploadUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by xzjie on 2017/7/24.
 */
public class OSSFileManager {

    private String dir = null;
    private String rootPath = null;
    private String[] allowFiles = null;
    private int count = 0;

    public OSSFileManager ( Map<String, Object> conf ) {

        this.rootPath = (String)conf.get( "rootPath" );
        this.dir = (String)conf.get( "dir" );
        this.allowFiles = this.getAllowFiles( conf.get("allowFiles") );
        this.count = (Integer)conf.get( "count" );

    }

    public State listFile (int index ) {
        State state = null;

        Collection<File> list = null;
        String prefix = this.dir;
        List<String> objectList = OSSUploadUtils.list( prefix);

        if ( index < 0 || index > objectList.size() ) {
            state = new MultiState( true );
        } else {
            Object[] fileList = Arrays.copyOfRange( objectList.toArray(), index, index + this.count );
            state = this.getState( fileList );
        }

        state.putInfo( "start", index );
        state.putInfo( "total", objectList.size() );

        return state;

    }

    // 处理ailiyun数据
    private State getState ( Object[] files ) {

        MultiState state = new MultiState( true );
        BaseState fileState = null;

        for ( Object obj : files ) {
            if ( obj == null ) {
                break;
            }
            fileState = new BaseState( true );
            fileState.putInfo( "url",obj.toString() );
            state.addState( fileState );
        }

        return state;

    }


    private String[] getAllowFiles ( Object fileExt ) {

        String[] exts = null;
        String ext = null;

        if ( fileExt == null ) {
            return new String[ 0 ];
        }

        exts = (String[])fileExt;

        for ( int i = 0, len = exts.length; i < len; i++ ) {

            ext = exts[ i ];
            exts[ i ] = ext.replace( ".", "" );

        }

        return exts;

    }
}
