package com.xzjie.cms.core.repository;

import com.xzjie.cms.article.model.ArticleHot;
import com.xzjie.cms.model.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Map;

/**
 * @author vito
 * @date : 2022/9/23 12:26 PM
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity,ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

//    Page<Map<String,Object>> findPageMap(Pageable pageable);
}
