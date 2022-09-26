package com.xzjie.cms.navigation.service;

import com.xzjie.cms.core.service.BaseService;
import com.xzjie.cms.dto.NodeTree;
import com.xzjie.cms.navigation.model.Navigation;

import java.util.List;
import java.util.Set;

public interface NavigationService extends BaseService<Navigation> {

    List<Navigation> getNavigation();

    List<Navigation> getNavigations(Long pid, boolean enabled);

    List<Navigation> getNavigation(Long pid);

    List<NodeTree> getNavigationTree(Long pid);

    void delete(Set<Long> ids);
}
