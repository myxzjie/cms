package com.xzjie.cms.repository;

import com.xzjie.cms.core.repository.BaseRepository;
import com.xzjie.cms.model.Label;
import com.xzjie.cms.vo.LabelVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LabelRepository extends BaseRepository<Label, Long> {

    @Query(nativeQuery = true, value = "select * from cms_label a inner join cms_article_label b on a.id= b.label_id where b.article_id =#{#articleId}")
    List<Label> findByArticleId(Long articleId);

    @Query(nativeQuery = true, value = "SELECT * FROM cms_label a LEFT JOIN  (\n" +
            "SELECT label_id, count(article_id) total FROM cms_article_label WHERE id >= 1\n" +
            "GROUP BY label_id) b ON a.id = b.label_id")
    List<LabelVo> findLabelAll();
}
