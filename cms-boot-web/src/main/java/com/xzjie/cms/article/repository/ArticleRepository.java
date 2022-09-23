package com.xzjie.cms.article.repository;

import com.xzjie.cms.article.model.Article;
import com.xzjie.cms.core.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ArticleRepository extends BaseRepository<Article, Long> {

    @Query(nativeQuery = true, value = "select * from cms_article a where a.id < :#{#id} and category_id = :#{#categoryId} order by id desc limit 0,1")
    Article findByIdLessThanAndCategoryId(Long id, Long categoryId);

    @Query(nativeQuery = true, value = "select * from cms_article a where a.id > :#{#id} and category_id = :#{#categoryId} order by id desc limit 0,1")
    Article findByIdGreaterThanAndCategoryId(Long id, Long categoryId);

    @Modifying
    @Transactional
    @Query("update Article set countPraise = countPraise + 1 where id= :id")
    int updatePraise(Long id);


    Page<Article> findByLabels_idIn(List<Long> labelIds, Pageable pageable);
}
