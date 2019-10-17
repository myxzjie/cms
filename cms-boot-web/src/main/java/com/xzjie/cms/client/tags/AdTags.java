package com.xzjie.cms.client.tags;

import com.xzjie.cms.client.freemarker.AbstractTemplateModel;
import com.xzjie.cms.client.freemarker.annotation.FreemarkerComponent;
import com.xzjie.cms.model.Ad;
import com.xzjie.cms.service.AdService;
import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by xzjie on 2017/8/18.
 */
@FreemarkerComponent("ad")
public class AdTags extends AbstractTemplateModel {

    @Autowired
    private AdService adService;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        SimpleScalar positionCode = (SimpleScalar) params.getOrDefault("positionCode", "");
        List<Ad> ads = adService.getAdByPositionCode(positionCode.getAsString());

        env.setVariable("ads", wrap(ads));
        if (body != null) {
            body.render(env.getOut());
        }
    }
}
