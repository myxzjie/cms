package com.xzjie.cms.system.auth.repository;

import com.xzjie.cms.system.auth.model.Social;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Vito
 * @since 2022/6/14 1:31 上午
 */
public interface SocialRepository extends JpaRepository<Social, Long>, JpaSpecificationExecutor<Social> {

    Social findByUuid(String uuid);
}
