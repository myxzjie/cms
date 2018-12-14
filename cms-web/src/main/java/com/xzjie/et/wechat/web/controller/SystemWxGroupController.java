package com.xzjie.et.wechat.web.controller;

import com.xzjie.common.web.utils.MapResult;
import com.xzjie.core.utils.StringUtils;
import com.xzjie.et.core.web.BaseController;
import com.xzjie.et.wechat.model.WxAccountFollow;
import com.xzjie.et.wechat.model.WxGroup;
import com.xzjie.et.wechat.model.WxMessage;
import com.xzjie.et.wechat.service.WxAccountFollowService;
import com.xzjie.et.wechat.service.WxGroupService;
import com.xzjie.et.wechat.service.WxMessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 鹰视视科技: www.dev56.com
 *
 * @author: xzjie
 * @create: 2018-12-09 20:31
 **/
@Controller
@RequestMapping("${web.adminPath}/wx-group")
public class SystemWxGroupController extends BaseController {
    private final Logger LOG = LogManager.getLogger(getClass());
    @Autowired
    private WxMessageService wxMessageService;
    @Autowired
    private WxGroupService wxGroupService;
    @Autowired
    private WxAccountFollowService wxAccountFollowService;

    @RequestMapping(value = {"", "/", "index"})
    public String groupView(Long messageId, Long groupId, ModelMap model) {
        WxMessage message = wxMessageService.get(messageId);
        List<WxGroup> groups = wxGroupService.getWxGroupList(getSiteId());
        if (groups.size() > 0 && groupId == null) {
            groupId = groups.get(0).getId();
        }
        WxGroup group = wxGroupService.get(groupId);

        List<WxAccountFollow> accountFollows = wxAccountFollowService.getAccountFollowByGroupId(groupId);

        model.put("model", message);
        model.put("groups", groups);
        model.put("messageId", messageId);
        model.put("groupId", groupId);
        model.put("group", group);
        model.put("accountFollows", accountFollows);
        return getRemoteView("wechat/wx_group/wx_group");
    }

    @RequestMapping("addview")
    public String addview(Model model) {
        return getRemoteView("wechat/wx_group/wx_group_edit");
    }


    @RequestMapping("edit/{id}")
    public String editView(@PathVariable Long id, Model model) {
        WxGroup group = wxGroupService.get(id);
        model.addAttribute("model", group);
        return getRemoteView("wechat/wx_button/wx_button_edit");
    }

    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(WxGroup model) {
        if (StringUtils.isBlank(model.getName())) {
            return MapResult.mapError("1401");
        }

        try {
            model.setSiteId(getSiteId());
            model.setCreateDate(new Date());
            wxGroupService.save(model);
            return MapResult.mapOK("1400");
        } catch (Exception e) {
            LOG.error("添加公众菜单错误.", e);
        }

        return MapResult.mapError("1406");
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public Map<String, Object> update(WxGroup model) {

        if (StringUtils.isBlank(model.getName())) {
            return MapResult.mapError("1401");
        }
        try {
            if (wxGroupService.update(model)) {
                return MapResult.mapOK("1407");
            }
        } catch (Exception e) {
            LOG.error("修改公众号菜单错误.", e);
        }
        return MapResult.mapError("1408");
    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Long id) {
        try {
            wxGroupService.delete(id);
            return MapResult.mapOK("1410");
        } catch (Exception e) {
            LOG.error("公众号菜单删除错误.", e);
        }

        return MapResult.mapError("1409");
    }

    @RequestMapping("follow/add")
    @ResponseBody
    public Map<String, Object> addGroupFollow(@RequestParam("groupId") Long groupId, @RequestParam("followIds[]") List<Long> followIds) {
        if (groupId == null || followIds.size() < 1) {
            return MapResult.mapError("23");
        }
        try {
            if (wxGroupService.batchGroupFollow(groupId, followIds)) {
                return MapResult.mapOK();
            } else {
                return MapResult.mapError("20");
            }
        } catch (Exception e) {
            LOG.error("group follow add error.", e);
        }
        return MapResult.mapError();
    }
}
