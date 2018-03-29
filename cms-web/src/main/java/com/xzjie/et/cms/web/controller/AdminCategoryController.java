package com.xzjie.et.cms.web.controller;

import com.xzjie.core.utils.StringUtils;
import com.xzjie.et.cms.model.Category;
import com.xzjie.et.cms.service.CategoryService;
import com.xzjie.common.web.utils.MapResult;
import com.xzjie.et.core.web.BaseController;
import com.xzjie.mybatis.page.Page;
import com.xzjie.mybatis.page.PageEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xzjie on 2017/8/18.
 */
@Controller
@RequestMapping("${web.adminPath}/category")
public class AdminCategoryController extends BaseController {
    private final Logger LOG = LogManager.getLogger(AdminCategoryController.class);
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"", "/", "index"})
    public String index(Map<String, Object> modelView) {
        return getRemoteView("cms/category/category_index");
    }

    @RequestMapping("addview")
    public String addView() {
        return getRemoteView("cms/category/category_edit");
    }

    @RequestMapping(value = "edit/{id}")
    public String editView(@PathVariable Long id, Model model) {
        Category category = categoryService.get(id);
        if (category.getCategoryPId() == 0) {
            category.setCategoryPId(null);
        }
        model.addAttribute("model", category);
        return getRemoteView("cms/category/category_edit");
    }

    @RequestMapping("datapage")
    @ResponseBody
    public Map<String, Object> dataPage(Category model, Page page) {
        PageEntity<Category> pageEntity = new PageEntity<Category>();

        model.setSiteId(getSiteId());

        pageEntity.setT(model);
        pageEntity.setPage(page);
        try {
            PageEntity<Category> res = categoryService.getListPage(pageEntity);

            return MapResult.bootPage(res.getRows(), res.getPage());
        } catch (Exception e) {
            LOG.error("获得栏目数据错误：{}", e.getMessage());
        }
        return MapResult.mapError("901");
    }

    @RequestMapping(value = {"tree"})
    @ResponseBody
    public Map<String, Object> tree(Category model, Map<String, Object> modelView) {
        model.setSiteId(getSiteId());
        List<Category> list = categoryService.getList(model);

        return MapResult.mapOK(list);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Category model) {

        if (StringUtils.isBlank(model.getCategoryName())) {
            return MapResult.mapError("902");
        }
        if (model.getCategoryPId() == null) {
            model.setCategoryPId(0L);
        }

        if (model.getCategoryOrder() == null) {
            model.setCategoryOrder(1);
        }
        model.setCreateDate(new Date());
        model.setState(1);
        model.setSiteId(getSiteId());
        try {
            categoryService.save(model);
//            getRedisService().remove(ConstantsUtils.TE_NAVS_KEY+getSiteId());
            return MapResult.mapOK("903");
        } catch (Exception e) {
            LOG.error("添加栏目错误：{}", e.getMessage());
        }

        return MapResult.mapError("904");
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(Category model) {
        try {
            categoryService.update(model);
//            getRedisService().remove(ConstantsUtils.TE_NAVS_KEY+getSiteId());
            return MapResult.mapOK("906");
        } catch (Exception e) {
            LOG.error("修改栏目错误：{}", e.getMessage());
        }
        return MapResult.mapError("905");
    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public Map<String, Object> dataPage(@PathVariable Long id) {
        try {
            categoryService.delete(id);
//            getRedisService().remove(ConstantsUtils.TE_NAVS_KEY+getSiteId());
            return MapResult.mapOK("322");
        } catch (Exception e) {
            LOG.error("用户删除错误:{}", e.getMessage());
        }

        return MapResult.mapError("321");
    }
}
