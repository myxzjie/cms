package com.xzjie.cms.client.tags;

import com.xzjie.cms.client.freemarker.AbstractTemplateModel;
import com.xzjie.cms.client.freemarker.annotation.FreemarkerComponent;
import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@FreemarkerComponent("request")
public class RequestTag extends AbstractTemplateModel {

    @Autowired
    private HttpServletRequest request;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] templateModels, TemplateDirectiveBody body) throws TemplateException, IOException {
        String alias = ((SimpleScalar) params.getOrDefault("alias", new SimpleScalar("currentURI"))).getAsString();
        String currentURI = request.getRequestURI();
        env.setVariable(alias, wrap(currentURI));
        if (body != null) {
            body.render(env.getOut());
        }
    }
}
