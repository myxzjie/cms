/**
 * radp-cms
 * @Title: AccountServiceImpl.java 
 * @Package com.xzjie.gypt.system.service.impl
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年6月18日
 */
package com.xzjie.gypt.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.gypt.cms.dao.SiteMapper;
import com.xzjie.gypt.cms.model.Site;
import com.xzjie.gypt.cms.service.SiteService;
import com.xzjie.gypt.common.page.PageEntity;

/**
 * @className AccountServiceImpl.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年6月18日 下午5:30:06 
 * @version V0.0.1 
 */
@Service("siteService")
public class SiteServiceImpl implements SiteService{

	@Autowired
	private SiteMapper siteMapper;
	
	@Override
	public boolean save(Site record) {
		record.setCreateDate(new Date());
		record.setState(1); //1 正常 0失效
		return siteMapper.insert(record) > 0;
	}

	@Override
	public void batchSave(List<Site> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(Site record) {
		return siteMapper.update(record) > 0;
	}

	@Override
	public void batchUpdate(List<Site> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDeleteById(List<Long> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Site record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDelete(List<Site> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Site get(Long id) {
		return siteMapper.getById(id);
	}

	@Override
	public Site get(Site record) {
		return siteMapper.getEntity(record);
	}

	@Override
	public List<Site> getList(Site record) {
		return siteMapper.findList(record);
	}

	@Override
	public List<Site> getAllList(Site record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Site> getListPage(PageEntity<Site> record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Site getSiteByOrgId(Long orgId) {
		Site record=new Site();
		record.setOrgId(orgId);
		
		return this.get(record);
	}

	

}
