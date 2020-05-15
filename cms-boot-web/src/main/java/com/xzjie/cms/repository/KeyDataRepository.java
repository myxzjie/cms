package com.xzjie.cms.repository;

import com.xzjie.cms.model.KeyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface KeyDataRepository extends JpaRepository<KeyData, Long>, JpaSpecificationExecutor<KeyData> {

    KeyData findByKey(String key);

    boolean existsByKey(String key);
}
