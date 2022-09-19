package com.xzjie.cms.system.role.serivce.impl;

import com.xzjie.cms.system.account.model.AccountRole;
import com.xzjie.cms.model.Permission;
import com.xzjie.cms.system.role.model.Role;
import com.xzjie.cms.system.account.repository.AccountRoleRepository;
import com.xzjie.cms.repository.PermissionRepository;
import com.xzjie.cms.system.role.repository.RoleRepository;
import com.xzjie.cms.system.role.serivce.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountRoleRepository accountRoleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> getRoleByUserId(Long userId) {
        return roleRepository.findRoleByUserId(userId);
    }

    @Override
    public Set<Role> getRoles(Long userId) {
        return roleRepository.findRoleByUserId(userId)
                .stream()
                .map(role -> new Role(role.getId(), role.getRoleCode(), role.getRoleName(), role.getRoleLevel()))
                .collect(Collectors.toSet());
    }

    @Override
    public Page<Role> getRole(Integer page, Integer size, Role query) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return roleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query == null) {
                return null;
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
    }

    @Override
    public List<Permission> getRolePermission(Long roleId) {
        return permissionRepository.findByRoleId(roleId);
    }

    @Transactional
    @Override
    public void save(Role role, List<Long> menus) {
        role.setState(1);
        roleRepository.save(role);

        menus.stream().forEach(menuId -> {
            Permission permission = new Permission();
            permission.setMenuId(menuId);
            permission.setRoleId(role.getId());
            permissionRepository.save(permission);
        });
    }

    @Transactional
    @Override
    public void update(Role role, List<Long> menus) {
        Role model = roleRepository.findById(role.getId()).orElseGet(Role::new);
        model.copy(role);
        roleRepository.save(model);

        permissionRepository.deleteByRoleId(role.getId());
        menus.stream().forEach(menuId -> {
            Permission permission = new Permission();
            permission.setMenuId(menuId);
            permission.setRoleId(role.getId());
            permissionRepository.save(permission);
        });
    }

    @Transactional
    @Override
    public void delete(Long roleId) {
        roleRepository.deleteById(roleId);
        permissionRepository.deleteByRoleId(roleId);
    }

    @Override
    public void saveAccount(List<AccountRole> accountRoles) {
        accountRoleRepository.saveAll(accountRoles);
    }

    @Override
    public void deleteAccountRole(Long userId) {
        accountRoleRepository.deleteByUserId(userId);
    }

    @Override
    public List<Long> getAccountRoleByUserId(Long userId) {
        return accountRoleRepository.findAccountRoleByUserId(userId);
    }


}
