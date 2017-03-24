package com.xzjie.freemarker;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateHashModel;

public class FreemarkerStaticModels extends HashMap<Object, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6003148524273066079L;
	private static FreemarkerStaticModels FREEMARKER_STATIC_MODELS;
	private Properties staticModels;

	private FreemarkerStaticModels() {

	}

	public static FreemarkerStaticModels getInstance() {
		if (FREEMARKER_STATIC_MODELS == null) {
			FREEMARKER_STATIC_MODELS = new FreemarkerStaticModels();
		}
		return FREEMARKER_STATIC_MODELS;
	}

	public Properties getStaticModels() {
		return staticModels;
	}

	public void setStaticModels(Properties staticModels) {
		if (this.staticModels == null && staticModels != null) {
			this.staticModels = staticModels;
			Set<String> keys = this.staticModels.stringPropertyNames();
			for (String key : keys) {
				FREEMARKER_STATIC_MODELS.put(key, useStaticPackage(this.staticModels.getProperty(key)));
			}
		}
	}

	public static TemplateHashModel useStaticPackage(String packageName) {
		try {
			// BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
			// Create the builder:
		    BeansWrapperBuilder builder = new BeansWrapperBuilder(Configuration.VERSION_2_3_23);
		    // Set desired BeansWrapper configuration properties:
		    builder.setUseModelCache(true);
		    builder.setExposeFields(true);
		    
		    // Get the singleton:
		    BeansWrapper wrapper = builder.build();
		    // You don't need the builder anymore.
			TemplateHashModel staticModels = wrapper.getStaticModels();
			TemplateHashModel fileStatics = (TemplateHashModel) staticModels.get(packageName);
			return fileStatics;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
