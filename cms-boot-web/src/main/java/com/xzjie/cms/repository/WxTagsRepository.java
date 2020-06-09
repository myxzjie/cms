package com.xzjie.cms.repository;

import com.xzjie.cms.model.WxTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxTagsRepository extends JpaRepository<WxTags, Long>, JpaSpecificationExecutor<WxTags> {

}
