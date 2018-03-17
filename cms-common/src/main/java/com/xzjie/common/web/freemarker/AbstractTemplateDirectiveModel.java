package com.xzjie.common.web.freemarker;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * Created by xzjie on 2017/7/8.
 */
public abstract class AbstractTemplateDirectiveModel  implements TemplateDirectiveModel {

    private DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_23);

    public TemplateModel wrap(Object obj) throws TemplateModelException {
        return builder.build().wrap(obj);
    }
}
