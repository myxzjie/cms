package com.xzjie.cms.system.menu.service.impl;

import com.xzjie.cms.system.menu.convert.MenuConverter;
import com.xzjie.cms.system.menu.repository.MenuRepository;
import com.xzjie.cms.system.menu.service.MenuService;
import com.xzjie.cms.system.menu.vo.MenuVo;
import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.dto.MenuMeta;
import com.xzjie.cms.dto.MenuRouter;
import com.xzjie.cms.dto.MenuTree;
import com.xzjie.cms.system.menu.model.Menu;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends AbstractService<Menu, MenuRepository> implements MenuService {

    @Override
    public List<MenuTree> getMenuTree(Long pid) {
        List<MenuTree> trees = new ArrayList<>();
        List<Menu> menus = baseRepository.findByPid(pid);
        menus.stream().forEach(menu -> {
            MenuTree tree = new MenuTree();
            tree.setId(menu.getId());
            tree.setLabel(menu.getName());
            tree.setChildren(getMenuTree(menu.getId()));
            trees.add(tree);
        });
        return trees;
    }

    @Override
    public List<MenuRouter> getMenuRouter(Set<String> roles) {
        List<MenuRouter> menuRouters = new ArrayList<>();
        List<Integer> types = Arrays.asList(1);
        List<Menu> menus = this.getMenuByRoles(roles, types);

        List<MenuRouter> routers = getTreeNodeList(menus);
        Map<Long, MenuRouter> menuMap = getTreeNodeMap(routers);

        routers.stream().forEach(router -> {
            if (menuMap.containsKey(router.getPid())) {
                MenuRouter parentRouter = menuMap.get(router.getPid());
//                parentRouter.setAlwaysShow(true);
                parentRouter.getChildren().add(router);
            } else {
                menuRouters.add(router);
            }
        });

        return menuRouters;
    }

    private List<MenuRouter> getTreeNodeList(List<Menu> menus) {
        return menus.stream().map(menu -> {
            MenuRouter menuRouter = new MenuRouter();
            menuRouter.setId(menu.getId());
            menuRouter.setPid(menu.getPid());
            menuRouter.setName(menu.getName());
            menuRouter.setPath(menu.getPath());
            menuRouter.setHidden(menu.getHidden());
            menuRouter.setRedirect(menu.getRedirect());
            menuRouter.setComponent(menu.getComponent());
            List<String> roles = null;
            if (menu.getPermission() != null) {
                String[] permissions = menu.getPermission().split(",");
                roles = Arrays.asList(permissions);
            }
            menuRouter.setMeta(new MenuMeta(menu.getComponentName(), menu.getIcon(), menu.getCache(), roles));
            if (menu.getPid() == 0) {
                menuRouter.setAlwaysShow(menu.getAlwaysShow());
            }

            return menuRouter;
        }).collect(Collectors.toList());
    }

    private Map<Long, MenuRouter> getTreeNodeMap(List<MenuRouter> routers) {
        return routers.stream()
                .collect(Collectors.toMap(MenuRouter::getId, router -> router));
    }

    @Override
    public List<MenuVo> getMenus() {
        List<MenuVo> menuResponses = new ArrayList<>();
        List<Menu> menus = baseRepository.findByPidOrderBySortDescIdAsc(0L);
        menus.stream().forEach(menu -> {
            MenuVo menuResponse = MenuConverter.INSTANCE.source(menu);
            menuResponse.setChildren(getTree(menu.getId()));
            menuResponses.add(menuResponse);
        });
        return menuResponses;
    }

    @Override
    public void delete(Set<Long> ids) {
        ids.stream().forEach(id -> {
            Set<Long> menuIds = getIds(id);
            baseRepository.delete(menuIds);
        });
    }

    @Override
    public List<Menu> getMenuByRoles(Set<String> roles, List<Integer> types) {
        return baseRepository.findMenuByRoles(roles, types);
    }

    private Set<Long> getIds(Long pid) {
        Set<Long> ids = new HashSet<>();
        ids.add(pid);
        List<Menu> menus = baseRepository.findByPid(pid);
        menus.stream().forEach(menu -> {
            ids.add(menu.getId());
            this.getIds(menu.getId());
        });
        return ids;
    }

    private List<MenuVo> getTree(Long pid) {
        List<MenuVo> menuResponses = new ArrayList<>();
        List<Menu> menus = baseRepository.findByPidOrderBySortDescIdAsc(pid);
        menus.stream().forEach(menu -> {
            MenuVo menuResponse = MenuConverter.INSTANCE.source(menu);
            List<MenuVo> children = getTree(menu.getId());
            menuResponse.setChildren(children);
            menuResponses.add(menuResponse);
        });
        return menuResponses;
    }

//    @Override
//    public boolean update(Menu obj) {
//        Menu model = baseRepository.findById(obj.getId()).orElseGet(Menu::new);
//        model.copy(obj);
//        baseRepository.save(model);
//        return true;
//    }
}
