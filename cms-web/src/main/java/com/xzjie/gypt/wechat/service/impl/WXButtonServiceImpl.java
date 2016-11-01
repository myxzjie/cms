/**
 * radp-cms
 * @Title: WXButtonServiceImpl.java 
 * @Package com.xzjie.gypt.wechat.service.impl
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年9月5日
 */
package com.xzjie.gypt.wechat.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.wechat.dao.WXButtonMapper;
import com.xzjie.gypt.wechat.entity.wxmuens.Button;
import com.xzjie.gypt.wechat.entity.wxmuens.CommonButton;
import com.xzjie.gypt.wechat.entity.wxmuens.ComplexButton;
import com.xzjie.gypt.wechat.entity.wxmuens.Menu;
import com.xzjie.gypt.wechat.entity.wxmuens.ViewButton;
import com.xzjie.gypt.wechat.model.WXButton;
import com.xzjie.gypt.wechat.service.WXButtonService;

import net.sf.json.JSONObject;

/**
 * @className WXButtonServiceImpl.java
 * @description TODO(添加描述) 
 * @author xzjie
 * @create 2016年9月5日 下午10:26:24 
 * @version V0.0.1 
 */
@Service("wxButtonService")
public class WXButtonServiceImpl implements WXButtonService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private WXButtonMapper wxButtonMapper;
	@Autowired
	private WechatHelper wechatHelper;

	@Override
	public boolean save(WXButton record) {
		record.setCreateDate(new Date());
		int row=wxButtonMapper.insert(record);
		return row > 0 ? true : false;
	}

	@Override
	public void batchSave(List<WXButton> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean update(WXButton record) {
		int row=wxButtonMapper.update(record);
		return row > 0 ? true : false;
	}

	@Override
	public void batchUpdate(List<WXButton> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Long id) {
		WXButton record = new WXButton();
		record.setButtonId(id);
		return wxButtonMapper.delete(record) > 0;
	}

	@Override
	public void batchDeleteById(List<Long> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(WXButton record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDelete(List<WXButton> records) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WXButton get(Long id) {
		return wxButtonMapper.getById(id);
	}

	@Override
	public WXButton get(WXButton record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WXButton> getList(WXButton record) {
		return wxButtonMapper.findList(record);
	}

	@Override
	public List<WXButton> getAllList(WXButton record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WXButton> getListPage(PageEntity<WXButton> record) {
		return wxButtonMapper.findListPage(record);
	}

	@Override
	public List<WXButton> getWXButtonTree(Long buttonId, Long userId) {
		WXButton button=new WXButton();
		button.setButtonId(buttonId);
		button.setUserId(userId);
		return wxButtonMapper.findWXButtonTree(button);
	}

	@Override
	public void syncButton(Long userId, String accessToken) throws Exception {
		WXButton record=new WXButton();
		record.setUserId(userId);
		record.setpButtonId(0L);
		List<WXButton> buttonList = this.getList(record);
		
		if(logger.isDebugEnabled()){
			logger.debug(".....一级菜单的个数是....." + buttonList.size());
		}
		
		Menu menu = new Menu();
		Button firstArr[] = new Button[buttonList.size()];
		for (int a = 0; a < buttonList.size(); a++) {
			WXButton entity = buttonList.get(a);
			
			WXButton childRecord=new WXButton();
			
			childRecord.setpButtonId( entity.getButtonId());
			childRecord.setUserId(userId);
			
			List<WXButton> childList = this.getList(childRecord);
			if(logger.isDebugEnabled()){
				logger.debug(".....二级菜单的个数是....." + childList.size());
			}
			
			if (childList.size() == 0) {
				if("view".equals(entity.getType())){
					ViewButton viewButton = new ViewButton();
					viewButton.setName(entity.getName());
					viewButton.setType(entity.getType());
					viewButton.setUrl(entity.getUrl());
					firstArr[a] = viewButton;
				}else if("click".equals(entity.getType())){
					CommonButton cb = new CommonButton();
					cb.setKey(entity.getButtonKey());
					cb.setName(entity.getName());
					cb.setType(entity.getType());
					firstArr[a] = cb;
				}
			
			} else {
				ComplexButton complexButton = new ComplexButton();
				complexButton.setName(entity.getName());

				Button[] secondARR = new Button[childList.size()];
				for (int i = 0; i < childList.size(); i++) {
					WXButton children = childList.get(i);
					String type = children.getType();
					if ("view".equals(type)) {
						ViewButton viewButton = new ViewButton();
						viewButton.setName(children.getName());
						viewButton.setType(children.getType());
						viewButton.setUrl(children.getUrl());
						secondARR[i] = viewButton;

					} else if ("click".equals(type)) {

						CommonButton cb1 = new CommonButton();
						cb1.setName(children.getName());
						cb1.setType(children.getType());
						cb1.setKey(children.getButtonKey());
						secondARR[i] = cb1;

					}

				}
				complexButton.setSub_button(secondARR);
				firstArr[a] = complexButton;
			}
		}
		menu.setButton(firstArr);
		
		JSONObject jsonMenu = JSONObject.fromObject(menu);
		
		wechatHelper.createButton(accessToken,jsonMenu.toString());
	}

	

}
