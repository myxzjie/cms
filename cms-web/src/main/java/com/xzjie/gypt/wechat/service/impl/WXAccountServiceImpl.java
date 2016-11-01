/**
 * radp-cms
 * @Title: WXAccountServiceImpl.java 
 * @Package com.xzjie.gypt.wechat.service.impl
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年9月2日
 */
package com.xzjie.gypt.wechat.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.wechat.dao.WXAccountMapper;
import com.xzjie.gypt.wechat.model.WXAccount;
import com.xzjie.gypt.wechat.service.WXAccountService;

/**
 * @className WXAccountServiceImpl.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年9月2日 下午11:18:51 
 * @version V0.0.1 
 */
@Service("wxAccountService")
public class WXAccountServiceImpl implements WXAccountService{
	
	@Autowired
	private WXAccountMapper wxAccountMapper;

	@Override
	public boolean save(WXAccount record) {
		record.setCreateDate(new Date());
		record.setState(1); //状态 1正常，0失败
		int row=wxAccountMapper.insert(record);
		return row>0?true:false;
	}

	@Override
	public void batchSave(List<WXAccount> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(WXAccount record) {
		int row=wxAccountMapper.update(record);
		return row>0?true:false;
	}

	@Override
	public void batchUpdate(List<WXAccount> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Long id) {
		WXAccount record=new WXAccount();
		record.setWxAccId(id);
		record.setState(0); //状态 1正常，0失败
		return this.update(record);
	}

	@Override
	public void batchDeleteById(List<Long> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(WXAccount record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDelete(List<WXAccount> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WXAccount get(Long id) {
		return wxAccountMapper.getById(id);
	}

	@Override
	public WXAccount get(WXAccount record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WXAccount> getList(WXAccount record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WXAccount> getAllList(WXAccount record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WXAccount> getListPage(PageEntity<WXAccount> record) {
		return wxAccountMapper.findListPage(record);
	}

	@Override
	public boolean isExist(Long userId) {
		WXAccount record = new WXAccount();
		record.setUserId(userId);
		int count = wxAccountMapper.exist(record);
		return count > 0 ? true : false;
	}

	@Override
	public WXAccount getWxAccountByUserId(Long userId) {
		return wxAccountMapper.findWxAccountByUserId(userId);
	}

}
