package com.xzjie.cms.system.account.repository;

import com.xzjie.cms.system.account.model.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRoleRepository extends JpaRepository<AccountRole, Long>, JpaSpecificationExecutor<AccountRole> {

    @Query(value = "select roleId from AccountRole where userId = :userId")
    List<Long> findAccountRoleByUserId(Long userId);

    void deleteByUserId(Long userId);
}
