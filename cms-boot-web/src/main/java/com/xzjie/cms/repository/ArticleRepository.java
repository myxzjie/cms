package com.xzjie.cms.repository;

import com.xzjie.cms.model.Account;
import com.xzjie.cms.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

//    List<Ad> findAdByPositionIdAndEnabled(Long positionId, boolean enabled);

//    default List<Ad> findAdByPositionId(Long positionId) {
//        return findAdByPositionIdAndEnabled(positionId, true);
//    }
}
