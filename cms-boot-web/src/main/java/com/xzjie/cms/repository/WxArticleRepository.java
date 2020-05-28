package com.xzjie.cms.repository;

import com.xzjie.cms.model.WxArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface WxArticleRepository extends JpaRepository<WxArticle, Long>, JpaSpecificationExecutor<WxArticle> {

    List<WxArticle> findByNewsId(String newsId);

    @Modifying
    @Transactional
    @Query(value = "delete from WxArticle a where a.id in (:ids) ")
    int deleteByIds(@Param("ids") List<Long> ids);

    @Modifying
    @Transactional
    @Query(value = "UPDATE WxArticle SET thumbMediaId = :thumbMediaId WHERE id = :id ")
    int updateThumbMediaId(String thumbMediaId, Long id);
}
