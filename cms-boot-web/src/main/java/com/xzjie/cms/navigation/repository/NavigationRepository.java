package com.xzjie.cms.navigation.repository;

import com.xzjie.cms.core.repository.BaseRepository;
import com.xzjie.cms.navigation.model.Navigation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface NavigationRepository extends BaseRepository<Navigation, Long> {

    List<Navigation> findByPid(Long pid);

    List<Navigation> findByPidAndEnabled(Long pid, Boolean enabled);

    @Modifying
    @Transactional
    @Query(value = " delete from Navigation n where n.id in (:ids) ")
    void delete(Set<Long> ids);
}
