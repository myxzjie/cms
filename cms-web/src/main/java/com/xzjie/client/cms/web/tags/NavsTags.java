package com.xzjie.client.cms.web.tags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.xzjie.cache.ehcache.service.SystemCacheManager;
import com.xzjie.client.cms.model.Navs;
//import com.xzjie.cache.redis.service.RedisService;
import com.xzjie.common.annotation.freemarker.FreemarkerComponent;
import com.xzjie.common.utils.SpringUtils;
import com.xzjie.common.web.freemarker.AbstractTemplateDirectiveModel;
import com.xzjie.core.utils.StringUtils;
import com.xzjie.et.cms.model.Category;
import com.xzjie.et.cms.service.CategoryService;

import com.xzjie.et.core.utils.ConstantsUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xzjie on 2017/8/27.
 */
@FreemarkerComponent("navs")
public class NavsTags extends AbstractTemplateDirectiveModel {

    @Autowired
    private CategoryService categoryService;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        if (params.get("id") == null)
            return;
        String id = params.get("id").toString();

        List<Navs> navs = SystemCacheManager.get(ConstantsUtils.TE_NAVS_KEY + id, List.class);

        if (navs == null) {
            Category model = new Category();
            model.setShowModes("1");
            model.setSiteId(Long.parseLong(id));
            List<Category> categorys = categoryService.getList(model);
            navs = getRootNodes(categorys);

            for (Navs nav : navs) {
                childNodes(categorys, nav);
            }
            SystemCacheManager.put(ConstantsUtils.TE_NAVS_KEY + id, navs);
        }

        env.setVariable("navs", wrap(navs));
        if (body != null) {
            body.render(env.getOut());
        }
    }

    private void childNodes(List<Category> list, Navs navs) {
        List<Navs> childs = new ArrayList<Navs>();
        for (Category category : list) {
            if (category.getCategoryPId() == null || category.getCategoryPId() == 0) {
                continue;
            }
            if (category.getCategoryPId() == navs.getId()) {
                Navs subs = getNavs(category);

                navs.setSub(true);

                childs.add(subs);
                childNodes(list, subs);
            }
        }
        sort(childs);
        navs.setSubNavs(childs);
    }

    private List<Navs> getRootNodes(List<Category> list) {
        List<Navs> roots = new ArrayList<Navs>();
        for (Category category : list) {
            if (category.getCategoryPId() == null || category.getCategoryPId() == 0) {

                roots.add(getNavs(category));
            }
        }
        sort(roots);
        return roots;
    }

    private Navs getNavs(Category category) {
        Navs navs = new Navs();
        navs.setId(category.getCategoryId());
        navs.setName(category.getCategoryName());
        if (StringUtils.isBlank(category.getHref())) {
            navs.setHref("/category/" + category.getCategoryId());
        } else {
            navs.setHref(category.getHref());
        }
        navs.setSort(category.getCategoryOrder());
        return navs;
    }

    private void sort(List<Navs> list) {
        Comparator<Navs> com = new Comparator<Navs>() {

            public int compare(Navs o1, Navs o2) {
                return (int) (o1.getSort() - o2.getSort());
            }
        };
        Collections.sort(list, com);
    }
}
