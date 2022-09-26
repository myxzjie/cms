package com.xzjie.cms.wechat.repository;

import com.xzjie.cms.wechat.model.WxFansTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface WxFansTagRepository extends JpaRepository<WxFansTag, Long>, JpaSpecificationExecutor<WxFansTag> {

    void deleteByTagId(Long tagId);

    void deleteByFansId(Long fansId);

    List<WxFansTag> findByFansId(Long fansId);
}
