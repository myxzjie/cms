package com.xzjie.cms.repository;

import com.xzjie.cms.core.repository.BaseRepository;
import com.xzjie.cms.enums.VerifyCodeScenes;
import com.xzjie.cms.enums.VerifyCodeType;
import com.xzjie.cms.model.VerifyCode;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VerifyCodeRepository extends BaseRepository<VerifyCode, Long> {
    VerifyCode findByTargetAndAndScenesAndType(String target, VerifyCodeScenes scenes, VerifyCodeType type);

    @Query(nativeQuery = true, value = "select v.* from sys_verify_code v where state=1 and create_date <= DATE_SUB(?1,INTERVAL ?2 MINUTE)")
    List<VerifyCode> findVerifyCode(LocalDateTime dateTime, Integer expiration);

    default List<VerifyCode> findVerifyCode(Integer expiration) {
        return this.findVerifyCode(LocalDateTime.now(), expiration);
    }
}
