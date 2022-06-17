package com.xzjie.cms.repository;

import com.xzjie.cms.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    @Query(nativeQuery = true, value = "select b.* from sys_account_role a inner join sys_role b on a.role_id=b.id where a.user_id = :#{#userId}")
    List<Role> findRoleByUserId(Long userId);
}
