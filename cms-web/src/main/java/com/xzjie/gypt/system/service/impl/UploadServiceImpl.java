package com.xzjie.gypt.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.system.dao.UploadMapper;
import com.xzjie.gypt.system.model.Upload;
import com.xzjie.gypt.system.service.UploadService;




@Service("uploadService")
public class UploadServiceImpl implements UploadService{

	@Autowired
	private UploadMapper uploadMapper;

	@Override
	public boolean save(Upload record) {
		record.setCreatedete(new Date());
		int row=uploadMapper.insert(record);
		return row>0?true:false;
	}

	@Override
	public void batchSave(List<Upload> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(Upload record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchUpdate(List<Upload> records) {
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
	public boolean delete(Upload record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDelete(List<Upload> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Upload get(Long id) {
		return uploadMapper.getById(id);
	}

	@Override
	public Upload get(Upload record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Upload> getList(Upload record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Upload> getAllList(Upload record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Upload> getListPage(PageEntity<Upload> record) {
		return uploadMapper.findListPage(record);
	}

	
	
}
