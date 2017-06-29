package com.xzjie.freemarker.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.xzjie.gypt.cms.model.Category;
import com.xzjie.gypt.cms.service.CategoryService;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class NavsDirective implements TemplateDirectiveModel {

	@Autowired
	private CategoryService categoryService;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		// gorup name
		SimpleScalar name = (SimpleScalar) params.get("name"); 
		//type
//		SimpleScalar type = (SimpleScalar) params.get("type"); 
		System.out.println(">>>>>>>>>>>>> ============navs name:"+name);
		List<Category> navs=categoryService.getCategoryTree(null,1L);
		System.out.println(">>>>>>>>>>>>> ============navs json:"+JSON.toJSONString(navs));
		DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_23);
		env.setVariable("navs", builder.build().wrap(navs));
        if (body != null) {  
            body.render(env.getOut());  
        }
	}

}
