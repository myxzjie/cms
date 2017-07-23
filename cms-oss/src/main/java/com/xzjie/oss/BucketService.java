package com.xzjie.oss;

import java.util.List;

import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.CannedAccessControlList;

public interface BucketService {

	/**
	 * 创建一个Bucket，如果创建成功，则返回新的Bucket <br>
	 * 
	 * Bucket命名规则：<br>
	 * 只能包括小写字母，数字，短横线（-） <br>
	 * 必须以小写字母或者数字开头 <br>
	 * 长度必须在3-63字节之间
	 * @param bucketName
	 * @return
	 */
	Bucket create(String bucketName);
	
	/**
	 * 判断Bucket是否存在
	 * @param bucketName
	 * @return
	 */
	boolean bucketExist(String bucketName);
	
	/**
	 * 删除Bucket<br>
	 * 如果Bucket不为空（Bucket中有Object），则Bucket无法删除，必须清空Bucket后才能成功删除
	 * @param bucketName
	 */
	void delete(String bucketName);
	
	/**
	 *  Bucket权限控制 
	 * 
	 * public-read-write:
	 * 任何人（包括匿名访问）都可以对该bucket中的object进行上传、下载和删除操作；所有这些操作产生的费用由该bucket的创建者承担
	 * ，请慎用该权限。<br>
	 * public-read: 只有该bucket的创建者可以对该bucket内的Object进行写操作（包括上传和删除）；任何人（包括匿名访问）
	 * 可以对该bucket中的object进行读操作。<br>
	 * private: 只有该bucket的创建者才可以访问此Bukcet。其他人禁止对此Bucket做任何操作。<br>
	 * @param bucketName
	 * @param acl CannedAccessControlList是枚举类型，包含三个值： Private 、 PublicRead 、 PublicReadWrite ，它们分别对应相关权限
	 */
	void setBucketAcl( String bucketName, CannedAccessControlList acl);
	
	/**
	 * 列出用户所有的Bucket
	 * @return
	 */
	List<Bucket> list() ;
}
