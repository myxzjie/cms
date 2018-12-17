package com.xzjie.common.web.exception;

import com.xzjie.common.web.freemarker.AbstractTemplateDirectiveModel;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * 鹰视视科技: www.dev56.com
 *
 * @author: xzjie
 * @create: 2018-12-14 23:09
 **/
public class FreemarkerExceptionHandler extends AbstractTemplateDirectiveModel implements TemplateExceptionHandler {
    private final Logger LOG = LogManager.getLogger(getClass());

    @Override
    public void handleTemplateException(TemplateException e, Environment environment, Writer writer) throws TemplateException {
        LOG.error("system error: ", e);
        environment.setVariable("exception", wrap(e));
        throw new ViewException("freemarker error", e);
    }

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {

    }
}
