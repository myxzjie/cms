package com.xzjie.oss.stream;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aliyun.oss.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;


public class ObjectOSSUpload extends DefaultOSSUpload {

    private static Logger LOG = LoggerFactory.getLogger(ObjectOSSUpload.class);

    public ObjectOSSUpload() {
        ossUpload = new UploadFileImpl();
    }

    public ObjectOSSUpload(String bucketName) {
        this();
        setBucketName(bucketName);
    }

    @Override
    public String upload(InputStream stream) {
        ossUpload.upload(stream);
        String url = getDomain();
        //close();
        return url;
    }

    @Override
    public String getDomain() {
        // 设置URL过期时间为1小时
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
        // 生成URL
        URL url = client.generatePresignedUrl(getBucketName(), getKey(), expiration);

        return url.toString().substring(0, url.toString().indexOf("?"));
    }

    /**
     * 列出存储空间中的文件
     *
     * @param prefix
     * @return
     */
    @Override
    public List<String> list(String prefix) {
        final int maxKeys = 20;
        // 是否循环的标识
        boolean hasNext = false;
        // 设定结果从Marker之后按字母排序的第一个开始返回
        String marker = "";
        List<String> filePathList = new ArrayList<String>();

        // 构造ListObjectsRequest请求
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(getBucketName());
        // 是一个用于对Object名字进行分组的字符。所有名字包含指定的前缀且第一次出现Delimiter字符之间的object作为一组元素:
        // CommonPrefixes
        //listObjectsRequest.setDelimiter(delimiter);
        // 限定此次返回object的最大数，如果不设定，默认为100，MaxKeys取值不能大于1000
        listObjectsRequest.setMaxKeys(maxKeys);
        // 限定返回的object key必须以Prefix作为前缀。注意使用prefix查询时，返回的key中仍会包含Prefix
        listObjectsRequest.setPrefix(prefix);

        do {
            // 设定结果从Marker之后按字母排序的第一个开始返回
            listObjectsRequest.setMarker(marker);
            // 获取指定bucket下的所有Object信息
            ObjectListing sublisting = client.listObjects(listObjectsRequest);
            // 如果Bucket中的Object数量大于100，则只会返回100个Object， 且返回结果中 IsTruncated
            // 为false
            if (sublisting.isTruncated()) {
                hasNext = true;
                marker = sublisting.getNextMarker();
            } else {
                hasNext = false;
                marker = "";
            }
            // // 遍历所有Object
            for (OSSObjectSummary objectSummary : sublisting.getObjectSummaries()) {
                // System.out.println(objectSummary.getKey());
                filePathList.add(objectSummary.getKey());
            }
        } while (hasNext);

        return filePathList;
    }


    private class UploadFileImpl implements OSSUpload {
        @Override
        public void upload(InputStream stream) {
            // 创建上传Object的Metadata
            ObjectMetadata meta = new ObjectMetadata();
            try {
                // 必须设置ContentLength
                meta.setContentLength((long) stream.available());
                // 用户自定义文件名称
                meta.addUserMetadata("filename", getKey());
                PutObjectResult result = client.putObject(getBucketName(), getKey(), stream);
                LOG.info(">> put result:{}", JSONObject.fromObject(result).toString());
            } catch (IOException e) {
                e.printStackTrace();
                LOG.error("上传OSS错误:{}", e.getMessage());
            }
        }
    }


}
