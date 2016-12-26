/**
 * ask_rear
 * @Title: Example.java 
 * @Package com.ask.rear.api.controller
 * @Description: TODO(添加描述) 
 * @Copyright: Copyright (c) 2016
 * @Company:
 * @author 作者 E-mail: 513961835@qq.com
 * @date 2016年1月29日
 */
package com.xzjie.gypt.api.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xzjie.gypt.api.model.ExampleModel;
import com.xzjie.gypt.api.model.api100001.Content;
import com.xzjie.gypt.api.model.api100001.Response;
import com.xzjie.gypt.api.model.test.TestModel;
import com.xzjie.gypt.api.model.test.TestModelResponse;
import com.xzjie.gypt.common.page.Page;
import com.xzjie.gypt.common.page.PageEntity;
import com.xzjie.gypt.common.protocol.Head;
import com.xzjie.gypt.common.utils.result.MapResult;
import com.xzjie.gypt.common.web.controller.BaseController;
import com.xzjie.gypt.system.model.Account;
import com.xzjie.gypt.system.model.Resource;
import com.xzjie.gypt.system.service.AccountService;
import com.xzjie.gypt.system.service.ResourceService;

/**
 * @className Example
 * @description TODO(添加描述)
 * @author xiaozj
 * @create 2016年1月29日 上午10:42:34
 * @version V0.0.1 
 */
@Controller
@RequestMapping(value="${web.apiPath}/example")
public class ExampleController extends BaseController{

	@Autowired
	private ResourceService accountService;
	
	//@ProtocolCode(interfaceId="100000")
	@RequestMapping(value="demo/{userId}")//,method=RequestMethod.POST
	public Map<String,Object> demo(@PathVariable String userId, ExampleModel model){
		
		return MapResult.mapOK(model,"OK");
	}
	
	@RequestMapping(value="demoitem")
	public Response demoItem(){
		Head head=new Head();
		Content content=new Content();
		List<Resource> list=accountService.getResourceUserByUserId(1L);
		content.setCiphertext("vcvfdlfksjdfksdfksdhfkjhfkflshfkjsfkjsnfkdfn dmfn");
		content.setResources(list);
		return new Response(head, content);
	}
	
	@RequestMapping(value="demoitem2")
	public com.xzjie.gypt.api.model.api10002.Response demoItem2(){
		Head head=new Head();
		com.xzjie.gypt.api.model.api10002.Content content=new com.xzjie.gypt.api.model.api10002.Content();
		List<Resource> list=accountService.getResourceUserByUserId(1L);
		for (Resource resource : list) {
			resource.setParent(null);
			resource.setChildren(null);
			resource.setResourceName(null);
			resource.setPerentName(null);
			resource.setCreateDate(null);
		}
		content.setCiphertext("vcvfdlfksjdfksdfksdhfkjhfkflshfkjsfkjsnfkdfn dmfn");
		content.setResources(list);
		return new com.xzjie.gypt.api.model.api10002.Response(head, content);
	}
	
	//@ProtocolCode(interfaceId="100001")
	@RequestMapping(value="demoitem3")
	public TestModelResponse demoItem3(){
		PageEntity<TestModel> pageEntity=new PageEntity<TestModel>();
		Page page=new Page();
		page.setCurrentPage(1);
		page.setShowCount(10);
		
		TestModel testModel=new TestModel();
		testModel.setId(1);
		testModel.setName("dd");
		testModel.setTime(new Date());
		pageEntity.setPage(page);
		pageEntity.setRecord(testModel);
		TestModelResponse response=new TestModelResponse();
		List<TestModel> list= new ArrayList<TestModel>(); //testService.findListPage(pageEntity);
		List<TestModel> list2= new ArrayList<TestModel>();
		
		TestModel testModel2=new TestModel();
		testModel2.setId(1);
		testModel2.setName("ddd");
		
		list2.add(testModel);
		testModel2.setModels(list2);
		
		list.add(testModel2);
		response.setItems(list);
		response.setPage(page);
		response.setHead(new Head());
		return response;
	}
}
