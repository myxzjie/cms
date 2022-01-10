package com.xzjie.cms.repository;

import com.xzjie.cms.dto.ArticleRecommendStatResult;
import com.xzjie.cms.model.ArticleHot;
import com.xzjie.cms.model.ArticleRecommendStat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


public interface ArticleRecommendStatRepository extends JpaRepository<ArticleRecommendStat, Long>, JpaSpecificationExecutor<ArticleHot> {

    @Query(value = "SELECT new com.xzjie.cms.dto.ArticleRecommendStatResult(b.id,b.articleId,a.title,b.sort,b.createDate) FROM ArticleRecommendStat b, Article a where a.id = b.articleId")
    Page<ArticleRecommendStatResult> findArticleRecommendStat(Pageable pageable);

//    ArticleHot findByArticleId(Long ArticleId);

    boolean existsByArticleId(Long articleId);
}
