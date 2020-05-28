package com.xzjie.cms.repository;

import com.xzjie.cms.model.WxArticleTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WxArticleTemplateRepository extends JpaRepository<WxArticleTemplate, Long>, JpaSpecificationExecutor<WxArticleTemplate> {
}
