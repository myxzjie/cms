package com.xzjie.et.system.web.controller;

import com.xzjie.common.web.utils.MapResult;
import com.xzjie.core.utils.StringUtils;
import com.xzjie.et.core.web.BaseController;
import com.xzjie.et.system.model.*;
import com.xzjie.et.system.service.ResourceService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xzjie on 2017/8/13.
 */
@Controller
@RequestMapping("${web.adminPath}/role")
public class AdminRoleController extends BaseController {

    private final Logger LOG = LogManager.getLogger(getClass());

    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = {"", "/", "index"})
    public String index(Map<String, Object> modelView) {
        return getRemoteView("role/role_index");
    }

    @RequestMapping(value = "edit/{id}")
    public String editView(@PathVariable Long id, Model model) {
        Role role = roleService.get(id);
        model.addAttribute("model", role);
        return getRemoteView("role/role_edit");
    }


    @RequestMapping("addview")
    public String addView() {

        return getRemoteView("role/role_edit");
    }

    @RequestMapping("authview/{id}")
    public String authView(@PathVariable Long id, Map<String, Object> modelView) {
        modelView.put("roleId", id);
        return getRemoteView("role/role_auth");
    }

    @RequestMapping("auth/{id}")
    @ResponseBody
    public Map<String, Object> auth(@PathVariable Long id) {
        return MapResult.mapOK(getTreeNode(id));
    }

    private List<TreeNode> getTreeNode(Long id) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        List<RoleResource> roleResources = roleService.getRoleResourceByRoleId(id);
        List<Resource> resources = resourceService.getList(new Resource());

        for (Resource resource : resources) {
            TreeNode node = new TreeNode();
            node.setId(resource.getResourceId());
            node.setpId(resource.getPerentResourceId());
            node.setName(resource.getResourceName());

            for (RoleResource roleResource : roleResources) {
                if (resource.getResourceId() == roleResource.getResourceId()) {
                    node.setChecked(true);
                }
            }

            list.add(node);
        }
        return list;
    }

    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(Role model) {
        if(StringUtils.isBlank(model.getRoleName())){
            return MapResult.mapError("107");
        }

        if(StringUtils.isBlank(model.getRoleCode())){
            return MapResult.mapError("108");
        }
        if(model.getRoleType()==null){
            return MapResult.mapError("109");
        }

        if(model.getRoleLevel()==null){
            return MapResult.mapError("110");
        }

        try {
            if(roleService.isExistByRoleCode(model.getRoleCode())){
                return MapResult.mapError("111");
            }

            roleService.save(model);

            return MapResult.mapOK("112");
        } catch (Exception e) {
            LOG.error("添加角色错误：{}", e.getMessage());
        }
        return MapResult.mapError("113");
    }

    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> update(Role model) {
        if(model.getRoleId()==null){
            return MapResult.mapError("116");
        }

        if(StringUtils.isBlank(model.getRoleName())){
            return MapResult.mapError("107");
        }

        if(StringUtils.isBlank(model.getRoleCode())){
            return MapResult.mapError("108");
        }
        if(model.getRoleType()==null){
            return MapResult.mapError("109");
        }

        if(model.getRoleLevel()==null){
            return MapResult.mapError("110");
        }

        try {
            roleService.update(model);
            return MapResult.mapOK("117");
        } catch (Exception e) {
            LOG.error("添加角色错误：{}", e.getMessage());
        }
        return MapResult.mapError("115");
    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable Long id) {
        try {
            roleService.delete(id);
            return MapResult.mapOK("103");
        } catch (Exception e) {
            return MapResult.mapError("102");
        }
    }

    @RequestMapping("data")
    @ResponseBody
    public Map<String, Object> data() {
        try {
            List<Role> roles =roleService.getList(new Role());
            return MapResult.mapOK(roles);
        } catch (Exception e) {
            return MapResult.mapError("114");
        }
    }

    @RequestMapping("datapage")
    @ResponseBody
    public Map<String, Object> dataPage(Role model, Page page) {
        PageEntity<Role> pageEntity = new PageEntity<Role>();
        pageEntity.setT(model);
        pageEntity.setPage(page);
        try {
            PageEntity<Role> res = roleService.getListPage(pageEntity);

            return MapResult.bootPage(res.getRows(), res.getPage());
        } catch (Exception e) {
            LOG.error("获得数据错误：{}", e.getMessage());
        }
        return MapResult.mapError("101");
    }

    @RequestMapping("auth/edit/{roleId}")
    @ResponseBody
    public Map<String, Object> authEdit(@PathVariable Long roleId, Long resourceId, boolean checked) {
        try {
            if (checked) {
                RoleResource model = new RoleResource();
                model.setRoleId(roleId);
                model.setResourceId(resourceId);
                roleService.saveRoleResource(model);
                return MapResult.mapOK("106");
            } else {
                roleService.deleteRoleResource(roleId, resourceId);
                return MapResult.mapOK("105");
            }
        } catch (Exception e) {
            return MapResult.mapError("104");
        }
    }
}
