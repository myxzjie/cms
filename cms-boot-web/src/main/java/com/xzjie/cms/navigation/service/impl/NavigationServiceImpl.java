package com.xzjie.cms.navigation.service.impl;

import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.dto.NodeTree;
import com.xzjie.cms.navigation.model.Navigation;
import com.xzjie.cms.navigation.repository.NavigationRepository;
import com.xzjie.cms.navigation.service.NavigationService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@CacheConfig(cacheNames = "navigation")
public class NavigationServiceImpl extends AbstractService<Navigation, NavigationRepository> implements NavigationService {

    @Override
    @Cacheable
    public List<Navigation> getNavigation() {
        boolean enabled = true;
        List<Navigation> navigations = this.getNavigation(0L, enabled);
        navigations.stream().forEach(navigation -> {
            navigation.setChildren(this.getChildrenNavigation(navigation.getId(), enabled));
        });
        return navigations;
    }

    @Override
    @Cacheable(key = "'navigations:'+#p0+#p1")
    public List<Navigation> getNavigations(Long pid, boolean enabled) {
        List<Navigation> navigations = this.getNavigation(pid, enabled);
        navigations.forEach(navigation -> {
            navigation.setChildren(this.getNavigations(navigation.getId(), enabled));
        });
        return navigations;
    }

    private List<Navigation> getChildrenNavigation(Long pid, Boolean enabled) {
        List<Navigation> navigations = this.getNavigation(pid, enabled);
        navigations.stream().forEach(navigation -> {
            List<Navigation> children = this.getChildrenNavigation(navigation.getId(), enabled);
            navigation.setChildren(children);
        });
        return navigations;
    }

    private List<Navigation> getNavigation(Long pid, Boolean enabled) {
        return baseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("pid").as(Long.class), pid));
            if (enabled != null) {
                predicates.add(criteriaBuilder.equal(root.get("enabled").as(Boolean.class), enabled));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, Sort.by("sort").descending());
    }

    @Override
    @Cacheable(key = "'navigations:'+#p0")
    public List<Navigation> getNavigation(Long pid) {
        List<Navigation> navigations = baseRepository.findByPid(pid);
        navigations.stream().forEach(navigation -> {
            navigation.setChildren(this.getNavigation(navigation.getId()));
        });
        return navigations;
    }

    @Override
    @Cacheable(key = "'navigations-tree:'+#p0")
    public List<NodeTree> getNavigationTree(Long pid) {
        List<NodeTree> trees = new ArrayList<>();
        List<Navigation> navigations = baseRepository.findByPid(pid);
        navigations.forEach(navigation ->
                trees.add(new NodeTree(navigation.getId(), navigation.getName(), this.getNavigationTree(navigation.getId())))
        );
        return trees;
    }

    @Override
    @CacheEvict(allEntries = true)
    public void delete(Set<Long> ids) {
        ids.forEach(id -> {
            Set<Long> navigationIds = getIds(id);
            baseRepository.delete(navigationIds);
        });
    }

    /**
     * 活动pid一下所以的节点
     *
     * @param pid
     * @return
     */
    private Set<Long> getIds(Long pid) {
        Set<Long> ids = new HashSet<>();
        ids.add(pid);
        List<Navigation> navigations = baseRepository.findByPid(pid);
        navigations.stream().forEach(navigation -> {
            ids.add(navigation.getId());
            this.getIds(navigation.getId());
        });
        return ids;
    }


    @Override
    @CacheEvict(allEntries = true)
    public <S extends Navigation> S save(S entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(allEntries = true)
    public <S extends Navigation> S update(S entity) {
        return super.update(entity);
    }
}
