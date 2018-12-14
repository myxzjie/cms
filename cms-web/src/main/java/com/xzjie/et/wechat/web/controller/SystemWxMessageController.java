package com.xzjie.et.wechat.web.controller;

import com.xzjie.common.web.utils.MapResult;
import com.xzjie.core.utils.StringUtils;
import com.xzjie.et.wechat.model.WxAccountFollow;
import com.xzjie.et.wechat.model.WxButton;
import com.xzjie.et.wechat.model.WxGroup;
import com.xzjie.et.wechat.model.WxMessage;
import com.xzjie.et.wechat.service.WxGroupService;
import com.xzjie.et.wechat.service.WxMessageService;
import com.xzjie.mybatis.page.Page;
import com.xzjie.mybatis.page.PageEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xzjie.et.core.web.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("${web.adminPath}/wx-message")
public class SystemWxMessageController extends BaseController {
    private final Logger LOG = LogManager.getLogger(getClass());

    @Autowired
    private WxMessageService wxMessageService;
    @Autowired
    private WxGroupService wxGroupService;

    @RequestMapping(value = {"", "/", "index"})
    public String indexView() {
        return getRemoteView("wechat/wx_message/wx_message");
    }


    @RequestMapping("addview")
    public String editView() {
        return getRemoteView("wechat/wx_message/wx_message_edit");
    }

    @RequestMapping("edit/{id}")
    public String editView(@PathVariable Long id, Model model) {
        WxMessage message = wxMessageService.get(id);
        model.addAttribute("model", message);
        return getRemoteView("wechat/wx_message/wx_message_edit");
    }

//    @RequestMapping("groupview/{id}")
//    public String groupView(@PathVariable Long id, Model model) {
//        WxMessage message = wxMessageService.get(id);
//        List<WxGroup> groups = wxGroupService.getList(new WxGroup());
//        model.addAttribute("model", message);
//        model.addAttribute("groups", groups);
//        return getRemoteView("wechat/wx_message/wx_group_message");
//    }

    @RequestMapping("datapage")
    @ResponseBody
    public Map<String, Object> dataPage(WxMessage model, Page page) {
        PageEntity<WxMessage> pageEntity = new PageEntity<WxMessage>();

        model.setSiteId(getSiteId());
        pageEntity.setT(model);
        pageEntity.setPage(page);
        try {
            PageEntity<WxMessage> res = wxMessageService.getListPage(pageEntity);

            return MapResult.bootPage(res.getRows(), res.getPage());
        } catch (Exception e) {
            LOG.error("获得数据错误.", e);
        }
        return MapResult.mapError("601");
    }

    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(WxMessage model) {
        if (StringUtils.isBlank(model.getTitle())) {
            return MapResult.mapError("1401");
        }
        if (StringUtils.isBlank(model.getMsgtype())) {
            return MapResult.mapError("1402");
        }

        if (StringUtils.isBlank(model.getContent())) {
            return MapResult.mapError("1404");
        }

        try {
            model.setSiteId(getSiteId());
            model.setCreateDate(new Date());

            wxMessageService.save(model);
            return MapResult.mapOK("1400");
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("错误.", e);
        }

        return MapResult.mapError("1406");
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public Map<String, Object> update(WxMessage model) {

        if (StringUtils.isBlank(model.getTitle())) {
            return MapResult.mapError("1401");
        }

        if (StringUtils.isBlank(model.getMsgtype())) {
            return MapResult.mapError("1403");
        }

        if (StringUtils.isBlank(model.getContent())) {
            return MapResult.mapError("1404");
        }

        try {
            if (wxMessageService.update(model)) {
                return MapResult.mapOK("1407");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("错误.", e);
        }
        return MapResult.mapError("1408");
    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Long id) {
        try {
            wxMessageService.delete(id);
            return MapResult.mapOK();
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("错误.", e);
        }
        return MapResult.mapError("22");
    }

    @RequestMapping("send")
    @ResponseBody
    public Map<String, Object> send(Long groupId, Long messageId) {
        try {
            wxMessageService.send(getSiteId(), groupId, messageId);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("错误.", e);
        }
        return MapResult.mapError("22");
    }
}
