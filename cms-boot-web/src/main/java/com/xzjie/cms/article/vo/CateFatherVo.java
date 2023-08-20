package com.xzjie.cms.article.vo;

import com.xzjie.cms.article.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author vito(xiaozijie)
 * @since 2023/8/18 8:28 AM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CateFatherVo {
    private Category father;
    private List<Category> categories;
}
