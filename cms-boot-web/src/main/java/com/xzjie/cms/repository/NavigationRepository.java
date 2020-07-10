package com.xzjie.cms.repository;

import com.xzjie.cms.model.Navigation;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface NavigationRepository extends JpaRepository<Navigation, Long>, JpaSpecificationExecutor {

    List<Navigation> findByPid(Long pid);

    List<Navigation> findByPidAndEnabled(Long pid, Boolean enabled);

    @Modifying
    @Transactional
    @Query(value = " delete from Navigation n where n.id in (:ids) ")
    void delete(Set<Long> ids);
}
