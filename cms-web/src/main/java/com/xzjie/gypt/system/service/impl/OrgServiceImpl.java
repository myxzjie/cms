/**
 * radp-cms
 * @Title: OrgServiceImpl.java 
 * @Package com.xzjie.gypt.system.service.impl
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年7月22日
 */
package com.xzjie.gypt.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.system.dao.OrgMapper;
import com.xzjie.gypt.system.model.Org;
import com.xzjie.gypt.system.service.OrgService;

/**
 * @className OrgServiceImpl.java
 * @description TODO(添加描述)
 * @author xzjie
 * @create 2016年7月22日 下午10:54:24
 * @version V0.0.1
 */
@Service("orgService")
public class OrgServiceImpl implements OrgService {

	@Autowired
	private OrgMapper orgMapper;

	@Override
	public boolean save(Org record) {
		
		if(record.getOrgPId()==null){
			record.setOrgPId(0L);
		}
		
		record.setCreateDate(new Date());
		record.setState(1); //状态 1正常，0失败
		
		int row = orgMapper.insert(record);
		return row > 0 ? true : false;
	}

	@Override
	public void batchSave(List<Org> records) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean update(Org record) {
		int row = orgMapper.update(record);
		return row > 0 ? true : false;
	}

	@Override
	public void batchUpdate(List<Org> records) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(Long id) {
		Org record=new Org();
		record.setState(0);//状态 1正常，0失败
		record.setOrgId(id);
		return this.update(record);
	}

	@Override
	public void batchDeleteById(List<Long> records) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(Org record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDelete(List<Org> records) {
		// TODO Auto-generated method stub

	}

	@Override
	public Org get(Long id) {
		return orgMapper.getById(id);
	}

	@Override
	public Org get(Org record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Org> getList(Org record) {
		return orgMapper.findList(record);
	}

	@Override
	public List<Org> getAllList(Org record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Org> getListPage(PageEntity<Org> record) {
		return orgMapper.findListPage(record);
	}

	@Override
	public boolean checkClientSecret(String clientSecret) {
		return orgMapper.checkClientSecret(clientSecret);
	}

	@Override
	public boolean checkOrgId(Long orgId) {
		return orgMapper.checkOrgId(orgId);
	}

}
