package com.xzjie.et.system.web.controller;

import com.xzjie.common.web.utils.MapResult;
import com.xzjie.et.core.web.BaseController;
import com.xzjie.et.system.model.Resource;
import com.xzjie.et.system.service.ResourceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by asus on 2017/8/10.
 */
@Controller
@RequestMapping("${web.adminPath}/resource")
public class SystemResourceController extends BaseController{
    private final Logger LOG = LogManager.getLogger(getClass());
    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value={"","/","index"})
    public String index(Map<String, Object> modelView){
        return getRemoteView("resource/resource_index");
    }


    @RequestMapping(value={"tree"})
    @ResponseBody
    public Map<String,Object> tree(Resource resource, Map<String, Object> modelView){
        List<Resource> list= resourceService.getList(resource);

        return MapResult.mapOK(list,"0");
    }

    @RequestMapping(value={"authtree"})
    @ResponseBody
    public Map<String,Object> authTree(){
        try {

            Long roleId = getPrincipal().getRoleId();
            List<Resource> list = resourceService.getResourceByRoleId(roleId);

            return MapResult.mapOK(list, "120");
        }catch (Exception e){
            LOG.error("获得用户菜单错误:{}",e.getMessage());
        }
        return  MapResult.mapError("121");
    }

}
