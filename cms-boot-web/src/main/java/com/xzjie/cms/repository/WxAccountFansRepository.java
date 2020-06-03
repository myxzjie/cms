package com.xzjie.cms.repository;

import com.xzjie.cms.model.WxAccountFans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxAccountFansRepository extends JpaRepository<WxAccountFans, Long>, JpaSpecificationExecutor<WxAccountFans> {

    boolean existsByOpenId(String openId);

    WxAccountFans findByOpenId(String openId);
}
