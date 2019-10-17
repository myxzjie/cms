package com.xzjie.cms.repository;

import com.xzjie.cms.model.Navigation;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface NavigationRepository extends JpaRepository<Navigation, Long>, JpaSpecificationExecutor {

    List<Navigation> findByPidAndEnabled(Long pid, Boolean enabled);

    default List<Navigation> findByPid(Long pid) {
        return findByPidAndEnabled(pid, true);
    }
}
