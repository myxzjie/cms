package com.xzjie.cms.article.dto;

import com.xzjie.cms.dto.TreeNode;
import lombok.Data;

@Data
public class CategoryTree extends TreeNode<CategoryTree> {

    private String categoryName;
}
