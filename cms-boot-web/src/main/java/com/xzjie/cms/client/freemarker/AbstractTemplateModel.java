package com.xzjie.cms.client.freemarker;

import freemarker.template.*;

/**
 * 鹰视科技: www.dev56.com
 *
 * @author: xzjie
 * @create: 2019-04-13 23:33
 **/
public abstract class AbstractTemplateModel implements TemplateDirectiveModel {

    private DefaultObjectWrapper defaultObjectWrapper;

    protected AbstractTemplateModel() {
        this(getObjectWrapper());
    }

    protected AbstractTemplateModel(DefaultObjectWrapper defaultObjectWrapper) {
        this.defaultObjectWrapper = defaultObjectWrapper;
        if (this.defaultObjectWrapper == null) {
            this.defaultObjectWrapper = getObjectWrapper();
        }
    }


    protected final TemplateModel wrap(Object obj) throws TemplateModelException {
        return defaultObjectWrapper.wrap(obj);
    }

    public static DefaultObjectWrapper getObjectWrapper() {
        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_28);
        return builder.build();
    }
}
