package com.xzjie.et.cms.web.controller;

import com.xzjie.core.utils.StringUtils;
import com.xzjie.et.cms.model.Article;
import com.xzjie.et.cms.model.ArticleContent;
import com.xzjie.et.cms.service.ArticleService;
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
import java.util.Map;

/**
 * Created by xzjie on 2017/8/31.
 */
@Controller
@RequestMapping("${web.adminPath}/article")
public class SystemArticleController extends BaseController {
    private final Logger LOG = LogManager.getLogger(getClass());

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value={"","/","index"})
    public String index(Map<String, Object> modelView){
        return getRemoteView("cms/article/article_index");
    }

    @RequestMapping("addview")
    public String addView() {
        return getRemoteView("cms/article/article_edit");
    }

    @RequestMapping(value = "edit/{id}")
    public String editView(@PathVariable Long id, Model model) {
        Article article= articleService.get(id);
        model.addAttribute("model",article);
        return getRemoteView("cms/article/article_edit");
    }

    @RequestMapping("datapage")
    @ResponseBody
    public Map<String, Object> dataPage(Article model, Page page) {
        PageEntity<Article> pageEntity = new PageEntity<Article>();

        model.setSiteId(getSiteId());

        pageEntity.setT(model);
        pageEntity.setPage(page);
        try {
            PageEntity<Article> res = articleService.getListPage(pageEntity);

            return MapResult.bootPage(res.getRows(), res.getPage());
        } catch (Exception e) {
            LOG.error("获得文章数据错误：{}", e.getMessage());
        }
        return MapResult.mapError("1001");
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Article model) {

        if(StringUtils.isBlank(model.getTitle())){
            return MapResult.mapError("1002");
        }
        if (model.getCategoryId() == null) {
            return MapResult.mapError("1003");
        }

        if (model.getSort() == null) {
            model.setSort(1);
        }
        model.setCreateDate(new Date());
        model.setStatus(1);
        model.setSiteId(getSiteId());
        model.setAuthor(getUserId());
        try {

            articleService.save(model);
            return MapResult.mapOK("1004");
        } catch (Exception e) {
            LOG.error("添加文章错误：{}", e.getMessage());
        }

        return MapResult.mapError("1005");
    }

    @RequestMapping(value="content/{id}")
    @ResponseBody
    public Map<String, Object> articleContent(@PathVariable Long id, Map<String, Object> modelView){
        ArticleContent content = articleService.getArticleContent(id);
        return MapResult.mapOK(content,"0");
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> update(Article model) {
        try {
            articleService.update(model);
            return MapResult.mapOK("1007");
        } catch (Exception e) {
            LOG.error("修改文章错误：{}", e.getMessage());
        }
        return MapResult.mapError("1006");
    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public Map<String, Object> dataPage(@PathVariable Long id) {
        try {
            articleService.delete(id);
            //getRedisService().remove(ConstantsUtils.REDIS_NAVS);
            return MapResult.mapOK("1008");
        }catch (Exception e){
            LOG.error("文章删除错误:{}",e.getMessage());
        }

        return  MapResult.mapError("1009");
    }
}
