package com.xzjie.et.system.web.controller;

import com.xzjie.common.web.utils.MapResult;
import com.xzjie.core.utils.RegexUtils;
import com.xzjie.core.utils.StringUtils;
import com.xzjie.et.core.web.BaseController;
import com.xzjie.et.system.model.Account;
import com.xzjie.et.system.model.Role;
import com.xzjie.et.system.service.AccountService;
import com.xzjie.et.system.service.RoleService;
import com.xzjie.mybatis.page.Page;
import com.xzjie.mybatis.page.PageEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * Created by xzjie on 2017/8/13.
 */
@Controller
@RequestMapping("${web.adminPath}/account")
public class SystemAccountController extends BaseController {

    private final Logger LOG = LogManager.getLogger(getClass());

    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = {"", "/", "index"})
    public String index(Map<String, Object> modelView) {
        return getRemoteView("account/account_index");
    }


    @RequestMapping("addview")
    public String authView(Map<String, Object> modelView) {
        return getRemoteView("account/account_edit");
    }

    @RequestMapping(value = "edit/{id}")
    public String editView(@PathVariable Long id, Model model) {
        Account account = accountService.get(id);
        Role role = roleService.getRoleByUserId(id);
        model.addAttribute("model", account);
        if (role != null) {
            model.addAttribute("roleId", role.getRoleId());
        }
        return getRemoteView("account/account_edit");
    }

    @RequestMapping(value = {"profile"})
    public String userProfile(Map<String, Object> modelView) {
        return getRemoteView("account/account_profile");
    }

    @RequestMapping(value = {"change-password"})
    public String changePassword(Map<String, Object> modelView) {
        return getRemoteView("account/change_password");
    }

    /**
     * 添加管理员用户
     *
     * @param model
     * @param password2
     * @param roleId
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(Account model, String password2, Long roleId) {
        if (StringUtils.isBlank(model.getName())) {
            return MapResult.mapError("308");
        }

        if (!RegexUtils.checkUserName(model.getName())) {
            return MapResult.mapError("323");
        }

        if (StringUtils.isNotBlank(model.getPhone()) && !RegexUtils.checkPhone(model.getPhone())) {
            return MapResult.mapError("307");
        }

        if (StringUtils.isNotBlank(model.geteMail()) && !RegexUtils.checkEmail(model.geteMail())) {
            return MapResult.mapError("306");
        }

        if (StringUtils.isBlank(model.getAvatar())) {
            return MapResult.mapError("309");
        }

        if (StringUtils.isBlank(model.getSex())) {
            return MapResult.mapError("305");
        }

        if (StringUtils.isBlank(model.getPassword())) {
            return MapResult.mapError("310");
        }

        if (StringUtils.isBlank(password2)) {
            return MapResult.mapError("311");
        }

        if (!password2.equals(model.getPassword())) {
            return MapResult.mapError("312");
        }

        if (roleId == null) {
            return MapResult.mapError("317");
        }

        try {

            if (accountService.isExistName(model.getName(), null)) {
                return MapResult.mapError("314");
            }

            if (accountService.isExistPhone(model.getPhone(), null)) {
                return MapResult.mapError("315");
            }

            if (accountService.isExistEmail(model.geteMail(), null)) {
                return MapResult.mapError("316");
            }

            model.setCreateDate(new Date());
            model.setState(1);
            model.setLocked(1);
            model.setCreateUser(getUserId());
            model.setStype("1");

            accountService.saveAccount(model, roleId);

            return MapResult.mapOK("318");

        } catch (Exception e) {
            LOG.error("添加管理员错误：{}", e.getMessage());
        }

        return MapResult.mapError("313");
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public Map<String, Object> update(Account model, Long roleId) {

        if (StringUtils.isBlank(model.geteMail())) {
            return MapResult.mapError("302");
        }

        if (!RegexUtils.checkEmail(model.geteMail())) {
            return MapResult.mapError("306");
        }

        if (StringUtils.isBlank(model.getPhone())) {
            return MapResult.mapError("303");
        }

        if (!RegexUtils.checkMobile(model.getPhone())) {
            return MapResult.mapError("307");
        }

        if (StringUtils.isBlank(model.getNickName())) {
            return MapResult.mapError("304");
        }

        if (StringUtils.isBlank(model.getSex())) {
            return MapResult.mapError("305");
        }

//		if(StringUtils.isBlank(model.getAvatar())){
//			model.setAvatar(null);
//		}

        try {
            if (accountService.updateAccount(model, roleId)) {

                return MapResult.mapOK("300");
            }
        } catch (Exception e) {
            LOG.error("修改用户信息错误:{}", e.getMessage());
        }
        return MapResult.mapError("301");
    }


    @RequestMapping("resetpwd/{id}")
    @ResponseBody
    public Map<String, Object> resetpwd(@PathVariable Long id) {
        try {
            String password = accountService.resetPassword(id);
            return MapResult.mapOK(password, "320");
        } catch (Exception e) {
            LOG.error("重置密码错误：{}", e.getMessage());
        }
        return MapResult.mapError("319");
    }

    @RequestMapping("updatepwd")
    @ResponseBody
    public Map<String, Object> updatepwd(Account model, String password2) {
        if (StringUtils.isBlank(model.getPassword())) {
            return MapResult.mapError("310");
        }

        if (StringUtils.isBlank(password2)) {
            return MapResult.mapError("311");
        }

        if (!password2.equals(model.getPassword())) {
            return MapResult.mapError("312");
        }

        try {
            accountService.updatePassword(getUserId(), getUsername(), model.getPassword());
            return MapResult.mapOK("320");
        } catch (Exception e) {
            LOG.error("重置密码错误：{}", e.getMessage());
        }
        return MapResult.mapError("319");
    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public Map<String, Object> dataPage(@PathVariable Long id) {
        try {
            accountService.delete(id);
            return MapResult.mapOK("322");
        } catch (Exception e) {
            LOG.error("用户删除错误:{}", e.getMessage());
        }

        return MapResult.mapError("321");
    }

    @RequestMapping("datapage")
    @ResponseBody
    public Map<String, Object> dataPage(Account model, Page page) {
        PageEntity<Account> pageEntity = new PageEntity<Account>();
        pageEntity.setT(model);
        pageEntity.setPage(page);
        try {
            PageEntity<Account> res = accountService.getListPage(pageEntity);

            return MapResult.bootPage(res.getRows(), res.getPage());
        } catch (Exception e) {
            LOG.error("获得数据错误：{}", e.getMessage());
        }
        return MapResult.mapError("601");
    }
}
