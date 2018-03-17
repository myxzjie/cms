package com.xzjie.et.ad.web.controller;

import com.xzjie.core.utils.RegexUtils;
import com.xzjie.core.utils.StringUtils;
import com.xzjie.et.ad.service.AdPositionService;
import com.xzjie.et.ad.service.AdService;
import com.xzjie.common.web.utils.MapResult;
import com.xzjie.et.core.web.BaseController;
import com.xzjie.mybatis.page.Page;
import com.xzjie.mybatis.page.PageEntity;
import com.xzjie.et.ad.model.Ad;
import com.xzjie.et.ad.model.AdPosition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("${web.adminPath}/ad")
public class AdminAdController extends BaseController {

    private final Logger LOG = LogManager.getLogger(AdminAdController.class);

    @Autowired
    private AdService adService;
    @Autowired
    private AdPositionService adPositionService;

    @RequestMapping(value = {"", "/", "index"})
    public String index() {
        return getRemoteView("ad/ad_index");
    }

    @RequestMapping("addview")
    public String addView() {
        return getRemoteView("ad/ad_add");
    }

    @RequestMapping("edit/{id}")
    public String editView(@PathVariable Long id, Map<String, Object> map) {
        Ad model = adService.get(id);
        AdPosition position = adPositionService.get(model.getPositionId());

        map.put("position", position);
        map.put("model", model);
        return getRemoteView("ad/ad_add");
    }


    /**
     * 广告位置页面
     *
     * @return
     */
    @RequestMapping(value = {"position", "position/index"})
    public String position() {
        return "ad/ad_position_index";
    }

    @RequestMapping("position/addview")
    public String positionView() {
        return "ad/ad_position_add";
    }


    /**
     * 广告位修改页面
     *
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("position/{id}")
    public String positionEditView(@PathVariable Long id, Map<String, Object> map) {

        AdPosition model = adPositionService.get(id);
        map.put("model", model);
        return "ad/ad_position_add";
    }

    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> save(Ad model) {

        if (StringUtils.isBlank(model.getAdName())) {
            return MapResult.mapError("453", "广告名称不能为空");
        }

        if (StringUtils.isBlank(model.getAdCode())) {
            return MapResult.mapError("453", "广告图片不能为空");
        }

        if (StringUtils.isNotBlank(model.getEmail()) && !RegexUtils.checkEmail(model.getEmail())) {
            return MapResult.mapError("454", "联系人Email不合法");
        }

        if (StringUtils.isNotBlank(model.getPhone()) && !RegexUtils.checkPhone(model.getPhone())) {
            return MapResult.mapError("455", "联系人电话不合法");
        }

        try {
            model.setSiteId(getSiteId());
            model.setCreateDate(new Date());
            adService.save(model);
            return MapResult.mapOK("451");
        } catch (Exception e) {
            LOG.error("添加广告错误：{}", e.getMessage());
        }
        return MapResult.mapError("452");
    }

    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> update(Ad model){
        if (StringUtils.isBlank(model.getAdName())) {
            return MapResult.mapError("453", "广告名称不能为空");
        }

        if (StringUtils.isBlank(model.getAdCode())) {
            return MapResult.mapError("453", "广告图片不能为空");
        }

        if (StringUtils.isNotBlank(model.getEmail()) && !RegexUtils.checkEmail(model.getEmail())) {
            return MapResult.mapError("454", "联系人Email不合法");
        }

        if (StringUtils.isNotBlank(model.getPhone()) && !RegexUtils.checkPhone(model.getPhone())) {
            return MapResult.mapError("455", "联系人电话不合法");
        }

        try {
            adService.update(model);
            return MapResult.mapOK("456");
        } catch (Exception e) {
            LOG.error("修改广告信息错误：{}", e.getMessage());
        }
        return MapResult.mapError("457");
    }

    @RequestMapping("position/add")
    @ResponseBody
    public Map<String, Object> saveAdPosition(AdPosition model) {
        model.setUserId(getUserId());
        try {
            model.setSiteId(getSiteId());
            Long id = adPositionService.save2(model);
            return MapResult.mapOK(id, "400");
        } catch (Exception e) {
            LOG.error("添加广告位错误：{}", e.getMessage());
        }
        return MapResult.mapError("401");
    }

    @RequestMapping("position/delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Long id) {
        try {
            adPositionService.delete(id);
            return MapResult.mapOK(id, "403");
        } catch (Exception e) {
            LOG.error("删除广告位错误：{}", e.getMessage());
        }
        return MapResult.mapError("404");
    }
    
    @RequestMapping("delete/{id}")
    @ResponseBody
    public Map<String, Object> adDelete(@PathVariable Long id) {
        try {
            adService.delete(id);
            return MapResult.mapOK(id, "403");
        } catch (Exception e) {
            LOG.error("删除广告位错误：{}", e.getMessage());
        }
        return MapResult.mapError("404");
    }

    @RequestMapping("datapage")
    @ResponseBody
    public Map<String, Object> adDataPage(Ad model, Page page) {
        PageEntity<Ad> pageEntity = new PageEntity<Ad>();
        model.setSiteId(getSiteId());
        pageEntity.setT(model);
        pageEntity.setPage(page);
        try {
            PageEntity<Ad> res = adService.getListPage(pageEntity);

            return MapResult.bootPage(res.getRows(), res.getPage());
        } catch (Exception e) {
            LOG.error("获得广告数据错误：{}", e.getMessage());
        }
        return MapResult.mapError("450");
    }

    /**
     * 分页获得广告位置的数据
     *
     * @param model
     * @param page
     * @return
     */
    @RequestMapping("position/datapage")
    @ResponseBody
    public Map<String, Object> positionDataPage(AdPosition model, Page page) {
        PageEntity<AdPosition> pageEntity = new PageEntity<AdPosition>();

        model.setSiteId(getSiteId());
        pageEntity.setT(model);
        pageEntity.setPage(page);
        try {
            PageEntity<AdPosition> res = adService.getPositionListPage(pageEntity);

            return MapResult.bootPage(res.getRows(), res.getPage());
        } catch (Exception e) {
            LOG.error("获得广告位数据错误：{}", e.getMessage());
        }
        return MapResult.mapError("402");
    }
}
