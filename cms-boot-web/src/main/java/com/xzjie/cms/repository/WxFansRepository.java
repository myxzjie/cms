package com.xzjie.cms.repository;

import com.xzjie.cms.core.repository.BaseRepository;
import com.xzjie.cms.model.WxAccountFans;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

public interface WxFansRepository extends BaseRepository<WxAccountFans, Long> {

    boolean existsByOpenId(String openId);

    WxAccountFans findByOpenId(String openId);


    @Query(nativeQuery = true, value = "SELECT * FROM (SELECT f.* FROM wx_account_fans f LEFT JOIN wx_fans_tag t ON f.id = t.fans_id) A")
    Page<WxAccountFans> findFansAll(@Nullable Specification<WxAccountFans> specification, Pageable pageable);
}
