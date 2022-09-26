package com.xzjie.cms.core.repository;

import com.xzjie.cms.core.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author vito
 * @date : 2022/9/23 12:26 PM
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity,ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

//    Page<Map<String,Object>> findPageMap(Pageable pageable);
}
