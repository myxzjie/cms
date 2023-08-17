package com.xzjie.cms.article.vo;

import com.xzjie.cms.article.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author vito(xiaozijie)
 * @since 2023/8/17 1:04 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesVo {
    private Category cate;
    private List<CaseVo> categories;
}
