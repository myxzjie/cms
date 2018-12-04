package com.xzjie.client.ad.web.tags;

import com.xzjie.common.annotation.freemarker.FreemarkerComponent;
import com.xzjie.common.utils.SpringUtils;
import com.xzjie.common.web.freemarker.AbstractTemplateDirectiveModel;
import com.xzjie.et.ad.model.Ad;
import com.xzjie.et.ad.service.AdService;
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
public class AdTags extends AbstractTemplateDirectiveModel {

    @Autowired
    private AdService adService;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        SimpleScalar id = (SimpleScalar) params.get("id");
        List<Ad> ads = adService.getAdByPositionId(Long.parseLong(id.toString()));

        env.setVariable("ads", wrap(ads));
        if (body != null) {
            body.render(env.getOut());
        }
    }
}
