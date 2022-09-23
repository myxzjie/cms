package com.xzjie.cms.system.role.serivce;

import com.xzjie.cms.system.role.model.Role;
import com.xzjie.cms.system.account.model.AccountRole;
import com.xzjie.cms.model.Permission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface RoleService {

    List<Role> getRoles();

    List<Role> getRoleByUserId(Long userId);

    Set<Role> getRoles(Long userId);

    Page<Role> getRole(Integer page, Integer size, Role query);

    List<Permission> getRolePermission(Long roleId);

    void save(Role role, List<Long> menus);

    void update(Role role, List<Long> menus);

    void delete(Long roleId);

    void saveAccount(List<AccountRole> accountRoles);

    void deleteAccountRole(Long userId);

    List<Long> getAccountRoleByUserId(Long userId);
}
