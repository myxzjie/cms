package com.xzjie.cms.service.impl;

import com.xzjie.cms.convert.MenuConverter;
import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.dto.MenuMeta;
import com.xzjie.cms.dto.MenuResponse;
import com.xzjie.cms.dto.MenuRouter;
import com.xzjie.cms.dto.MenuTree;
import com.xzjie.cms.model.Menu;
import com.xzjie.cms.repository.MenuRepository;
import com.xzjie.cms.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
//@CacheConfig(cacheNames = "menu")
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl extends AbstractService<Menu, Long> implements MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuConverter menuConverter;

    @Override
    protected JpaRepository getRepository() {
        return menuRepository;
    }

//    private final RoleService roleService;

//    @Override
//    @Cacheable
//    public List<MenuDTO> queryAll(MenuQueryCriteria criteria){
////        Sort sort = new Sort(Sort.Direction.DESC,"id");
//        return menuMapper.toDto(menuRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
//    }
//
//    @Override
//    @Cacheable(key = "#p0")
//    public MenuDTO findById(long id) {
//        Menu menu = menuRepository.findById(id).orElseGet(Menu::new);
//        ValidationUtil.isNull(menu.getId(),"Menu","id",id);
//        return menuMapper.toDto(menu);
//    }
//
//    @Override
//    public List<MenuDTO> findByRoles(List<RoleSmallDTO> roles) {
//        Set<Long> roleIds = roles.stream().map(RoleSmallDTO::getId).collect(Collectors.toSet());
//        LinkedHashSet<Menu> menus = menuRepository.findByRoles_IdInAndTypeNotOrderBySortAsc(roleIds, 2);
//        return menus.stream().map(menuMapper::toDto).collect(Collectors.toList());
//    }
//
//    @Override
//    @CacheEvict(allEntries = true)
//    public MenuDTO create(Menu resources) {
//        if(menuRepository.findByName(resources.getName()) != null){
//            throw new EntityExistException(Menu.class,"name",resources.getName());
//        }
//        if(StringUtils.isNotBlank(resources.getComponentName())){
//            if(menuRepository.findByComponentName(resources.getComponentName()) != null){
//                throw new EntityExistException(Menu.class,"componentName",resources.getComponentName());
//            }
//        }
//        if(resources.getIFrame()){
//            String http = "http://", https = "https://";
//            if (!(resources.getPath().toLowerCase().startsWith(http)||resources.getPath().toLowerCase().startsWith(https))) {
//                throw new BadRequestException("外链必须以http://或者https://开头");
//            }
//        }
//        return menuMapper.toDto(menuRepository.save(resources));
//    }
//
//    @Override
//    @CacheEvict(allEntries = true)
//    public void update(Menu resources) {
//        if(resources.getId().equals(resources.getPid())) {
//            throw new BadRequestException("上级不能为自己");
//        }
//        Menu menu = menuRepository.findById(resources.getId()).orElseGet(Menu::new);
//        ValidationUtil.isNull(menu.getId(),"Permission","id",resources.getId());
//
//        if(resources.getIFrame()){
//            String http = "http://", https = "https://";
//            if (!(resources.getPath().toLowerCase().startsWith(http)||resources.getPath().toLowerCase().startsWith(https))) {
//                throw new BadRequestException("外链必须以http://或者https://开头");
//            }
//        }
//        Menu menu1 = menuRepository.findByName(resources.getName());
//
//        if(menu1 != null && !menu1.getId().equals(menu.getId())){
//            throw new EntityExistException(Menu.class,"name",resources.getName());
//        }
//
//        if(StringUtils.isNotBlank(resources.getComponentName())){
//            menu1 = menuRepository.findByComponentName(resources.getComponentName());
//            if(menu1 != null && !menu1.getId().equals(menu.getId())){
//                throw new EntityExistException(Menu.class,"componentName",resources.getComponentName());
//            }
//        }
//        menu.setName(resources.getName());
//        menu.setComponent(resources.getComponent());
//        menu.setPath(resources.getPath());
//        menu.setIcon(resources.getIcon());
//        menu.setIFrame(resources.getIFrame());
//        menu.setPid(resources.getPid());
//        menu.setSort(resources.getSort());
//        menu.setCache(resources.getCache());
//        menu.setHidden(resources.getHidden());
//        menu.setComponentName(resources.getComponentName());
//        menu.setPermission(resources.getPermission());
//        menu.setType(resources.getType());
//        menuRepository.save(menu);
//    }
//
//    @Override
//    public Set<Menu> getDeleteMenus(List<Menu> menuList, Set<Menu> menuSet) {
//        // 递归找出待删除的菜单
//        for (Menu menu1 : menuList) {
//            menuSet.add(menu1);
//            List<Menu> menus = menuRepository.findByPid(menu1.getId());
//            if(menus!=null && menus.size()!=0){
//                getDeleteMenus(menus, menuSet);
//            }
//        }
//        return menuSet;
//    }
//
//    @Override
//    @CacheEvict(allEntries = true)
//    @Transactional(rollbackFor = Exception.class)
//    public void delete(Set<Menu> menuSet) {
//        for (Menu menu : menuSet) {
//            roleService.untiedMenu(menu.getId());
//            menuRepository.deleteById(menu.getId());
//        }
//    }
//

    //    @Cacheable(key = "'tree'")
    @Override
    public List<MenuTree> getMenuTree(Long pid) {
        List<MenuTree> trees = new ArrayList<>();
        List<Menu> menus = menuRepository.findByPid(pid);
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
        List<Menu> menus = menuRepository.findMenuByRoles(roles);

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


//        menus.stream().forEach(menu -> {
//            MenuRouter menuRouter = new MenuRouter();
//            if (StringUtils.isBlank(menu.getComponentName())) {
//                menuRouter.setName(menu.getName());
//            } else {
//                menuRouter.setName(menu.getComponentName());
//            }
//            menuRouter.setPath(menu.getPath());
//            menuRouter.setHidden(menu.getHidden());
//            menuRouter.setRedirect(menu.getRedirect());
//            menuRouter.setMeta(new MenuMeta(menu.getComponentName(), menu.getIcon(), menu.getCache()));
//            if (menu.getPid() == 0) {
//                menuRouter.setComponent("Layout");
//            } else {
//                menuRouter.setComponent(menu.getComponent());
//            }
//
//            List<MenuRouter> children = getMenuRouter(menu.getId(), roles);
//            if (children != null && children.size() > 0) {
//                menuRouter.setAlwaysShow(true);
//            } else {
//
//            }
//
//            menuRouter.setChildren(children);
//
//            menuRouters.add(menuRouter);
//        });
        return menuRouters;
//        return menuRouters(0L);
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
            menuRouter.setMeta(new MenuMeta(menu.getComponentName(), menu.getIcon(), menu.getCache()));
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
    public List<MenuResponse> getMenus() {
        List<MenuResponse> menuResponses = new ArrayList<>();
        List<Menu> menus = menuRepository.findByPid(0L);
        menus.stream().forEach(menu -> {
            MenuResponse menuResponse = menuConverter.source(menu);
            menuResponse.setChildren(getTree(menu.getId()));
            menuResponses.add(menuResponse);
        });
        return menuResponses;
    }

    @Override
    public void delete(Set<Long> ids) {
        ids.stream().forEach(id -> {
            Set<Long> menuIds = getIds(id);
            menuRepository.delete(menuIds);
        });
    }

    private Set<Long> getIds(Long pid) {
        Set<Long> ids = new HashSet<>();
        ids.add(pid);
        List<Menu> menus = menuRepository.findByPid(pid);
        menus.stream().forEach(menu -> {
            ids.add(menu.getId());
            this.getIds(menu.getId());
        });
        return ids;
    }

    private List<MenuResponse> getTree(Long pid) {
        List<MenuResponse> menuResponses = new ArrayList<>();
        List<Menu> menus = menuRepository.findByPid(pid);
        menus.stream().forEach(menu -> {
            MenuResponse menuResponse = menuConverter.source(menu);
            List<MenuResponse> children = getTree(menu.getId());
            menuResponse.setChildren(children);
            menuResponses.add(menuResponse);
        });
        return menuResponses;
    }

//    private List<MenuRouter> menuRouters(Long pid) {
//        List<MenuRouter> menuRouters = new ArrayList<>();
//        List<Menu> menus = menuRepository.findByPid(pid);
//        menus.stream().forEach(menu -> {
//            MenuRouter menuRouter = new MenuRouter();
//            if (StringUtils.isBlank(menu.getComponentName())) {
//                menuRouter.setName(menu.getName());
//            } else {
//                menuRouter.setName(menu.getComponentName());
//            }
//            menuRouter.setPath(menu.getPath());
//            menuRouter.setHidden(menu.getHidden());
//            menuRouter.setRedirect(menu.getRedirect());
//            menuRouter.setMeta(new MenuMeta(menu.getComponentName(), menu.getIcon(), menu.getCache()));
//            if (menu.getPid() == 0) {
//                menuRouter.setComponent("Layout");
//            } else {
//                menuRouter.setComponent(menu.getComponent());
//            }
//
//            List<MenuRouter> children = menuRouters(menu.getId());
//            if (children != null && children.size() > 0) {
//                menuRouter.setAlwaysShow(true);
//            } else {
//
//            }
//
//            menuRouter.setChildren(children);
//
//            menuRouters.add(menuRouter);
//        });
//        return menuRouters;
//    }


    @Override
    public boolean update(Menu obj) {
        Menu model = menuRepository.findById(obj.getId()).orElseGet(Menu::new);
        model.copy(obj);
        menuRepository.save(model);
        return true;
    }


//    @Override
//    @Cacheable(key = "'pid:'+#p0")
//    public List<Menu> findByPid(long pid) {
//        return menuRepository.findByPid(pid);
//    }
//
//    @Override
//    public Map<String,Object> buildTree(List<MenuDTO> menuDtos) {
//        List<MenuDTO> trees = new ArrayList<>();
//        Set<Long> ids = new HashSet<>();
//        for (MenuDTO menuDTO : menuDtos) {
//            if (menuDTO.getPid() == 0) {
//                trees.add(menuDTO);
//            }
//            for (MenuDTO it : menuDtos) {
//                if (it.getPid().equals(menuDTO.getId())) {
//                    if (menuDTO.getChildren() == null) {
//                        menuDTO.setChildren(new ArrayList<>());
//                    }
//                    menuDTO.getChildren().add(it);
//                    ids.add(it.getId());
//                }
//            }
//        }
//        Map<String,Object> map = new HashMap<>(2);
//        if(trees.size() == 0){
//            trees = menuDtos.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
//        }
//        map.put("content",trees);
//        map.put("totalElements", menuDtos.size());
//        return map;
//    }
//
//    @Override
//    public List<MenuVo> buildMenus(List<MenuDTO> menuDtos) {
//        List<MenuVo> list = new LinkedList<>();
//        menuDtos.forEach(menuDTO -> {
//            if (menuDTO!=null){
//                List<MenuDTO> menuDtoList = menuDTO.getChildren();
//                MenuVo menuVo = new MenuVo();
//                menuVo.setName(ObjectUtil.isNotEmpty(menuDTO.getComponentName())  ? menuDTO.getComponentName() : menuDTO.getName());
//                // 一级目录需要加斜杠，不然会报警告
//                menuVo.setPath(menuDTO.getPid() == 0 ? "/" + menuDTO.getPath() :menuDTO.getPath());
//                menuVo.setHidden(menuDTO.getHidden());
//                // 如果不是外链
//                if(!menuDTO.getIFrame()){
//                    if(menuDTO.getPid() == 0){
//                        menuVo.setComponent(StrUtil.isEmpty(menuDTO.getComponent())?"Layout":menuDTO.getComponent());
//                    }else if(!StrUtil.isEmpty(menuDTO.getComponent())){
//                        menuVo.setComponent(menuDTO.getComponent());
//                    }
//                }
//                menuVo.setMeta(new MenuMetaVo(menuDTO.getName(),menuDTO.getIcon(),!menuDTO.getCache()));
//                if(menuDtoList !=null && menuDtoList.size()!=0){
//                    menuVo.setAlwaysShow(true);
//                    menuVo.setRedirect("noredirect");
//                    menuVo.setChildren(buildMenus(menuDtoList));
//                    // 处理是一级菜单并且没有子菜单的情况
//                } else if(menuDTO.getPid() == 0){
//                    MenuVo menuVo1 = new MenuVo();
//                    menuVo1.setMeta(menuVo.getMeta());
//                    // 非外链
//                    if(!menuDTO.getIFrame()){
//                        menuVo1.setPath("index");
//                        menuVo1.setName(menuVo.getName());
//                        menuVo1.setComponent(menuVo.getComponent());
//                    } else {
//                        menuVo1.setPath(menuDTO.getPath());
//                    }
//                    menuVo.setName(null);
//                    menuVo.setMeta(null);
//                    menuVo.setComponent("Layout");
//                    List<MenuVo> list1 = new ArrayList<>();
//                    list1.add(menuVo1);
//                    menuVo.setChildren(list1);
//                }
//                list.add(menuVo);
//            }
//        }
//        );
//        return list;
//    }
//
//    @Override
//    public Menu findOne(Long id) {
//        Menu menu = menuRepository.findById(id).orElseGet(Menu::new);
//        ValidationUtil.isNull(menu.getId(),"Menu","id",id);
//        return menu;
//    }
//
//    @Override
//    public void download(List<MenuDTO> menuDtos, HttpServletResponse response) throws IOException {
//        List<Map<String, Object>> list = new ArrayList<>();
//        for (MenuDTO menuDTO : menuDtos) {
//            Map<String,Object> map = new LinkedHashMap<>();
//            map.put("菜单名称", menuDTO.getName());
//            map.put("菜单类型", menuDTO.getType() == 0 ? "目录" : menuDTO.getType() == 1 ? "菜单" : "按钮");
//            map.put("权限标识", menuDTO.getPermission());
//            map.put("外链菜单", menuDTO.getIFrame() ? "是" : "否");
//            map.put("菜单可见", menuDTO.getHidden() ? "否" : "是");
//            map.put("是否缓存", menuDTO.getCache() ? "是" : "否");
//            map.put("创建日期", menuDTO.getCreateTime());
//            list.add(map);
//        }
//        FileUtil.downloadExcel(list, response);
//    }
}
