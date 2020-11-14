package com.xzjie.cms.repository;

import com.xzjie.cms.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Long>, JpaSpecificationExecutor<Label> {

    @Query(nativeQuery = true, value = "select * from cms_label a inner join cms_article_label b on a.id= b.label_id where b.article_id =#{#articleId}")
    List<Label> findByArticleId(Long articleId);
}
