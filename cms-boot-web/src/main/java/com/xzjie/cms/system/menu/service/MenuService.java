package com.xzjie.cms.system.menu.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.system.menu.vo.MenuVo;
import com.xzjie.cms.dto.MenuRouter;
import com.xzjie.cms.dto.MenuTree;
import com.xzjie.cms.system.menu.model.Menu;

import java.util.List;
import java.util.Set;


public interface MenuService extends BaseService<Menu> {

    /**
     * 获取菜单树
     * @param pid
     * @return
     */
    List<MenuTree> getMenuTree(Long pid);

    List<MenuRouter> getMenuRouter(Set<String> roles);

    List<MenuVo> getMenus();

    void delete(Set<Long> ids);

    List<Menu> getMenuByRoles(Set<String> roles, List<Integer> types);
}
