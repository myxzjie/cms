package com.xzjie.cms.article.repository;

import com.xzjie.cms.article.dto.ArticleHotResult;
import com.xzjie.cms.article.model.ArticleHot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;


public interface ArticleHotRepository extends JpaRepository<ArticleHot, Long>, JpaSpecificationExecutor<ArticleHot> {

//    @Query(value = "SELECT new com.xzjie.cms.article.dto.ArticleHotResult(b.id,b.articleId,a.title,a.description,b.sort,b.createDate) " +
//            "FROM ArticleHot b, Article a where a.id = b.articleId")
//    Page<ArticleHotResult> findArticleHot(Pageable pageable);

//    ArticleHot findByArticleId(Long ArticleId);

    @Query(value = "SELECT b.id,b.article_id,a.title,a.image,a.description,b.sort,b.create_date FROM cms_article_hot b, cms_article a where a.id = b.article_id", nativeQuery = true)
    Page<Map<String, Object>> findArticleHot(Pageable pageable);

    boolean existsByArticleId(Long articleId);
}
