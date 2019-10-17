package com.xzjie.cms.client.tags;

import com.xzjie.cms.client.freemarker.AbstractTemplateModel;
import com.xzjie.cms.client.freemarker.annotation.FreemarkerComponent;
import com.xzjie.cms.model.Navigation;
import com.xzjie.cms.service.NavigationService;
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
 * Created by xzjie on 2017/8/27.
 */
@FreemarkerComponent("navigation")
public class NavigationTags extends AbstractTemplateModel {

    @Autowired
    private NavigationService navigationService;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        String alias = ((SimpleScalar) params.getOrDefault("alias", new SimpleScalar("navigations"))).getAsString();

        List<Navigation> navigations = navigationService.getNavigation();

        env.setVariable(alias, wrap(navigations));
        if (body != null) {
            body.render(env.getOut());
        }
    }

//    private void childNodes(List<Category> list, Navs navs) {
//        List<Navs> childs = new ArrayList<Navs>();
//        for (Category category : list) {
//            if (category.getCategoryPId() == null || category.getCategoryPId() == 0) {
//                continue;
//            }
//            if (category.getCategoryPId() == navs.getId()) {
//                Navs subs = getNavs(category);
//
//                navs.setSub(true);
//
//                childs.add(subs);
//                childNodes(list, subs);
//            }
//        }
//        sort(childs);
//        navs.setSubNavs(childs);
//    }
//
//    private List<Navs> getRootNodes(List<Category> list) {
//        List<Navs> roots = new ArrayList<Navs>();
//        for (Category category : list) {
//            if (category.getCategoryPId() == null || category.getCategoryPId() == 0) {
//
//                roots.add(getNavs(category));
//            }
//        }
//        sort(roots);
//        return roots;
//    }
//
//    private Navs getNavs(Category category) {
//        Navs navs = new Navs();
//        navs.setId(category.getCategoryId());
//        navs.setName(category.getCategoryName());
//        if (StringUtils.isBlank(category.getHref())) {
//            navs.setHref("/category/" + category.getCategoryId());
//        } else {
//            navs.setHref(category.getHref());
//        }
//        navs.setSort(category.getCategoryOrder());
//        return navs;
//    }
//
//    private void sort(List<Navs> list) {
//        Comparator<Navs> com = new Comparator<Navs>() {
//
//            public int compare(Navs o1, Navs o2) {
//                return (int) (o1.getSort() - o2.getSort());
//            }
//        };
//        Collections.sort(list, com);
//    }
}
